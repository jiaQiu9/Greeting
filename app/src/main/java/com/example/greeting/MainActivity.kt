package com.example.greeting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.greeting.ui.theme.GreetingTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GreetingTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text("")
                            }
                        )
                    },

                    ) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .padding(innerPadding),

                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,


                    ){
                        Greeting()
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var user_name by remember { mutableStateOf(" ") }
    var text by remember { mutableStateOf(" ") }
    val formatter = DateTimeFormatter.ofPattern("HH")

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Row(){
            OutlinedTextField(
                value = user_name,
                onValueChange = { user_name = it },
                label = { Text("") }
            )
        }
        Row(){
            Button(
                onClick = {
                    var nTime = LocalDateTime.now().format(formatter).toInt()
                    text = "Hello $user_name!"
                    user_name=""
                    if (6<=nTime && nTime <12){
                        text=text+"\n Good Morning!"
                    }
                    else if (12<=nTime && nTime<18){
                        text=text+"\nGood Afternoon!"
                    }
                    else if (18<=nTime && nTime<23){
                        text=text+"\nGood Evening!"
                    }
                    else if (23<=nTime && nTime<6){
                        text = text+"\nGood Middle of Night!"
                    }
                }
            ){
                Text("Enter")
            }
        }
        Row(){
            Text("$text")

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GreetingTheme {
        Greeting()
    }
}