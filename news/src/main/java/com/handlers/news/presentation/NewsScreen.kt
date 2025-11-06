package com.handlers.news.presentation


import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.firebase.auth.FirebaseAuth
import com.handlers.news.domain.Article

@Composable
fun NewsScreen(
    viewModel: NewsViewModel = viewModel(),
    onSignOut: () -> Unit
) {
    val ctx = LocalContext.current
    val items by viewModel.items
    val loading by viewModel.isLoading
    val err by viewModel.error
    val q by viewModel.query

    LaunchedEffect(Unit) { viewModel.loadHeadlines() }

    val userName = FirebaseAuth.getInstance().currentUser?.displayName

    Column(Modifier.fillMaxSize()) {
        // ✅ Header Row with Title + Logout
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                "Hello, $userName",
                style = MaterialTheme.typography.headlineSmall
            )

            Button(onClick = {
                FirebaseAuth.getInstance().signOut()
                onSignOut()
            }) {
                Text("Sign Off")
            }
        }

        OutlinedTextField(
            value = q,
            onValueChange = { viewModel.query.value = it },
            label = { Text("Search news") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            singleLine = true
        )
        Button(
            onClick = { viewModel.search() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) { Text("Search") }

        if (loading) {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (err != null) {
            Text(
                text = err ?: "",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(12.dp)
            ) {
                items(
                    items = items,
                    key = { it.url ?: it.title ?: it.hashCode() } // stable identity
                ) { art ->
                    ArticleRow(
                        article = art,
                        onOpen = {
                            val i = Intent(Intent.ACTION_VIEW, Uri.parse(art.url))
                            ctx.startActivity(i)
                        }
                    )
                    Divider()
                }
            }
        }
    }
}

@Composable
private fun ArticleRow(
    article: Article,
    onOpen: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onOpen() }
            .padding(8.dp)
    ) {
        AsyncImage(
            model = article.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .size(96.dp)
                .padding(end = 12.dp)
        )
        Column(Modifier.weight(1f)) {
            Text(
                text = article.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            if (!article.description.isNullOrBlank()) {
                Spacer(Modifier.height(6.dp))
                Text(
                    text = article.description,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Spacer(Modifier.height(6.dp))
            Text(
                text = article.publishedAt,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}