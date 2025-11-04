package com.handlers.auth.data.repository

import android.content.Context
import android.util.Log
import com.handlers.auth.data.model.User
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.util.UUID


class UserRepository(private val context: Context) {

    private val fileName = "user_data.json"

    // --- Ensure JSON file exists ---
    private fun getUserFile(): File {
        val file = File(context.filesDir, fileName)
        if (!file.exists()) {
            val defaultJson = JSONObject().apply {
                put("users", JSONArray())
            }.toString()
            FileOutputStream(file).use {
                it.write(defaultJson.toByteArray())
            }
        }
        return file
    }

    // --- Read all users ---
    fun getAllUsers(): MutableList<User> {
        val file = getUserFile()
        val jsonString = file.readText()
        val jsonObject = JSONObject(jsonString)
        val usersArray = jsonObject.getJSONArray("users")

        val userList = mutableListOf<User>()
        for (i in 0 until usersArray.length()) {
            val userObj = usersArray.getJSONObject(i)
            userList.add(
                User(
                    userId = userObj.getString("userId"),
                    email = userObj.getString("email"),
                    password = userObj.getString("password")
                )
            )
        }
        return userList
    }

    // --- Add a new user (auto-generates userId) ---
    fun addUser(email: String, password: String): User? {
        val file = getUserFile()
        val jsonString = file.readText()
        val jsonObject = JSONObject(jsonString)
        val usersArray = jsonObject.getJSONArray("users")

        // Prevent duplicate emails
        val exists = (0 until usersArray.length()).any { i ->
            usersArray.getJSONObject(i).getString("email") == email
        }
        if (exists) return null

        val newUser = User(
            userId = UUID.randomUUID().toString(),
            email = email,
            password = password
        )

        val userJson = JSONObject().apply {
            put("userId", newUser.userId)
            put("email", newUser.email)
            put("password", newUser.password)
        }
        usersArray.put(userJson)

        val updatedJson = JSONObject().apply {
            put("users", usersArray)
        }.toString()

        FileOutputStream(file).use {
            it.write(updatedJson.toByteArray())
        }

        return newUser
    }

    // --- Validate login ---
    fun isValidUser(email: String, password: String): Boolean {
        val users = getAllUsers()
        return users.any { it.email == email && it.password == password }
    }

    // --- Get user by userId ---
    fun getUserById(userId: String): User? {
        val users = getAllUsers()
        return users.find { it.userId == userId }
    }
}