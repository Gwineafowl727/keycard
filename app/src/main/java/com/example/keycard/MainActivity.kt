package com.example.keycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.keycard.ui.theme.KeycardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KeycardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TopBar()
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = "Keycard for Ankidroid") }
    )
}

@Composable
fun CentralColumn(modifier: Modifier = Modifier) {

}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Text(
            text = "Hello world!",
            modifier = modifier
                .background(Color.Red)
                .padding(16.dp)
                .background(Color.Cyan)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    KeycardTheme {
        TopBar()
        Greeting()
    }
}


/** Notes
 * activity: a "container" for one or more screens in the app; a unit of the app that user interacts with.
 * contains information such as
 * -is it active
 * -origin of launch
 * -and they all hold overridable functions: https://developer.android.com/guide/components/activities/activity-lifecycle
 *
 * so the activity called MainActivity is the entry point for our app.
 * android development nowadays usually has this as the sole activity in the whole app and swap between stuff using jetpack compose.
 *
 * Context is the bridge for an app (which is a separate linux process from everything else) to access system hardware, read files, start a background service, etc.
 * MainActivity itself is an activity which therefore is a Context, so you need to pass "this" wherever Context param is needed (and from then on, you pass whatever you make the variable name)
 * and you are able to do that once onCreate has been invoked.
 * MainActivity's context is called the Activity Context, and there is also an Application Context which is tied to the whole lifecycle of the application itself.
 * a Service itself is an activity as well, so it is also a context and therefore i can pass itself into other stuff to do my background service stuff.
 * basically, Activity Context for dialogs/views/other UI stuff, and then Application Context for background and behind the scenes stuff.
 */