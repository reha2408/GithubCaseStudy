package com.reha.casestudy.feature.jetpackcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reha.casestudy.R
import com.reha.casestudy.feature.jetpackcompose.data.Message
import com.reha.casestudy.feature.jetpackcompose.data.SampleData
import com.reha.casestudy.feature.jetpackcompose.ui.theme.GithubCaseStudyTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PageLayout()
        }
    }
}

@Composable
fun PageContainer(content: @Composable () -> Unit) {
    GithubCaseStudyTheme {
        Surface(color = MaterialTheme.colors.background) {
            content()
        }
    }
}

@Composable
fun PageLayout() {
    PageContainer {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = "LayoutsCodelab")
                    },
                    navigationIcon = {
                        IconButton({/* Ignoring onClick */ }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "back"
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = { /* doSomething() */ }) {
                            Icon(Icons.Filled.Favorite, contentDescription = null)
                        }
                    }
                )
            }
        ) { innerPadding ->
            BodyContent(Modifier.padding(innerPadding).padding(8.dp))
        }
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxSize()) {
        Text(text = "Hi there!")
        Text(text = "Thanks for going through the Layouts codelab")
    }
}

@Composable
fun Content() {
    Column(modifier = Modifier.fillMaxHeight()) {
        val conversations = SampleData.conversationSample
        val counterState = remember { mutableStateOf(0)}
        TopAppBar(
            title = {
                Text(text = "Page title", maxLines = 2)
            },
            navigationIcon = {
                IconButton({/* Ignoring onClick */ }) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        contentDescription = "back"
                    )
                }
            }
        )
        Conversation(messages = conversations, Modifier.weight(1f))
        Counter(
            count = counterState.value,
            updateCount = { counterState.value = it }
        )
    }
}

@Composable
fun Conversation(messages: List<Message>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(messages) { MessageCard(it) }
    }
}

@Composable
fun MessageCard(message: Message) {
    Row(modifier = Modifier
        .padding(all = 12.dp)
        .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(R.drawable.ex_android),
            contentDescription = "Logo",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember { mutableStateOf(false) }
        val surfaceColor: Color by animateColorAsState(
            if(isExpanded) MaterialTheme.colors.primary else MaterialTheme.colors.surface
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = message.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(
                shape = MaterialTheme.shapes.medium,
                elevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.body2,
                )
            }
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit) {
    Button(
        onClick = { updateCount(count + 1) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = if (count > 5) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant
        )
    ) {
        Text(
            text = "I've been clicked $count times",
        )
    }
}

@Composable
fun AuthorProfile(modifier: Modifier = Modifier) {
    Row(
        modifier
            .clip(RoundedCornerShape(4.dp))
            .background(color = MaterialTheme.colors.surface)
            .clickable(onClick = { /* Ignoring onClick */ })
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Surface( // it's like a placeholder.
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = painterResource(R.drawable.ex_android),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview(
    showBackground = true,
    name = "Author Profile Light Mode",
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Author Profile Dark Mode",
)
@Composable
fun AuthorProfileP() {
    PageContainer {
        val message = Message("Reha", "Hello",  "3 minutes ago")
        AuthorProfile()
    }
}

@Composable
fun PageUi() {
    PageContainer {
        Content()
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun DefaultPreview() {
    PageUi()
}

@Preview(
    name = "Light Mode 2",
    showBackground = true,
)
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode 2"
)
@Composable
@FullPagePreview("Light","Dark")
fun ScaffoldPreview() {
    PageLayout()
}

annotation class FullPagePreview(val light: String, val dark: String)