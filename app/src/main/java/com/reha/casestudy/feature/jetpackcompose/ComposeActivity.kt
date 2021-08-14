package com.reha.casestudy.feature.jetpackcompose

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.reha.casestudy.R
import com.reha.casestudy.feature.jetpackcompose.ui.theme.GithubCaseStudyTheme

class ComposeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PageUi()
        }
    }
}

data class Message(val author: String, val body: String)

@Composable
fun PageUi() {
    GithubCaseStudyTheme {
        Column(Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
        ) {
            MessageCard(Message("Reha", "Hey, take a look at Jetpack Compose, it's great!"))
            MessageCard(Message("Reha", "Hey, take a look at Jetpack Compose, it's great!"))
            MessageCard(Message("Reha", "Hey, take a look at Me!"))
            MessageCard(Message("Reha", "Hey, take a look at Jetpack Compose, it's great!"))
            MessageCard(Message("Reha", "Hey, take a look at Jetpack Compose, it's great!"))
            MessageCard(Message("Reha", "Hey, take a look at Me!"))
            MessageCard(Message("Reha", "Hey, take a look at Jetpack Compose, it's great!"))
            MessageCard(Message("Reha", "Hey, take a look at Jetpack Compose, it's great!"))
        }
    }
}

@Composable
fun MessageCard(message: Message) {
    Row(modifier = Modifier.padding(all = 12.dp).fillMaxWidth()) {
        Image(
            painter = painterResource(R.drawable.ex_android),
            contentDescription = "Logo",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = message.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.subtitle2
            )
            Spacer(modifier = Modifier.height(4.dp))
            Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                Text(
                    text = message.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.body2,
                )
            }
        }
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