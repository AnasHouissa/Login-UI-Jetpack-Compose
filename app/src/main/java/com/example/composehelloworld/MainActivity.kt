package com.example.composehelloworld

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutInput
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.TextUnit

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composehelloworld.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val configuration=LocalConfiguration.current
            val screenHeight = configuration.screenHeightDp.dp
            val screenWidth = configuration.screenWidthDp.dp

            ComposeHelloWorldTheme {
                Surface(
                    modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState()),
                    color = BlueGray
                ) {
                    Column(modifier = Modifier.fillMaxSize().padding(40.dp)) {
                        Image(

                            painter = painterResource(R.drawable.login),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
                                .size(170.dp)
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        CustomText(
                            "Welcome Back !",
                            32.sp,
                            Font(R.font.poppins_semibold),
                            TextAlign.Center, Orange
                        )
                        CustomText(
                            "Login to your account",
                            18.sp,
                            Font(R.font.poppins_medium),
                            TextAlign.Center, Orange
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        TextField()
                        Spacer(modifier = Modifier.height(10.dp))
                        PasswordField()
                        Spacer(modifier = Modifier.height(10.dp))
                        UnderlinedText("Forgot Password?", 14.sp, Red,TextAlign.End)
                        Spacer(modifier = Modifier.height(30.dp))
                        CustomButton("Login")
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(modifier = Modifier.padding(horizontal = 20.dp)) {
                            GoogleButton()
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()
                        ) {
                            Divider(color = Orange, modifier = Modifier.width(screenWidth/3))

                            Text(
                                "Or",
                                fontFamily = FontFamily(Font(R.font.poppins_regular)),
                                fontSize = 16.sp,
                                color = Orange,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                            Divider(color = Orange, modifier = Modifier.width(screenWidth/3))
                        }
                        Spacer(modifier = Modifier.height(20.dp))
                        CustomButton("Create an account")
                        Spacer(modifier = Modifier.height(20.dp))
                        UnderlinedText("Terms and conditions", 13.sp, LightGray,TextAlign.Center)


                    }
                }
            }
        }

    }

}

@Composable
fun CustomText(text: String, size: TextUnit, font: Font, textAlign: TextAlign, color: Color) {
    Text(
        text = text,
        fontSize = size,
        color = color,
        fontFamily = FontFamily(
            font
        ),
        modifier = Modifier.fillMaxWidth(),
        textAlign = textAlign
    )
}

@Composable
fun TextField() {
    var name by remember { mutableStateOf("") }
    OutlinedTextField(

        value = name,
        onValueChange = {
            name = it
        },
        leadingIcon = {
            Icon(
                Icons.Default.Email,
                contentDescription = null,
                tint = Orange
            )
        },
        label = {
            CustomText(
                "Email",
                14.sp,
                Font(R.font.poppins_medium),
                TextAlign.Start, Orange
            )
        },
        placeholder = {
            CustomText(
                "example@gmail.com",
                14.sp,
                Font(R.font.poppins_medium),
                TextAlign.Start, LightGray
            )
        },
        shape = RoundedCornerShape(24.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            textColor = Color.DarkGray
        )
    )
}

@Composable
fun PasswordField() {
    var pwd by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(

        value = pwd,
        onValueChange = {
            pwd = it
        },
        leadingIcon = {
            Icon(
                Icons.Default.Lock,
                contentDescription = null,
                tint = Orange
            )
        },
        label = {
            CustomText(
                "Password",
                14.sp,
                Font(R.font.poppins_medium),
                TextAlign.Start, Orange
            )
        },
        placeholder = {
            CustomText(
                "********",
                14.sp,
                Font(R.font.poppins_medium),
                TextAlign.Start, LightGray
            )
        },
        shape = RoundedCornerShape(24.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            textColor = Color.DarkGray
        ),

        trailingIcon = {

            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                if (passwordVisible)
                    Icon(
                        painter = painterResource(R.drawable.ic_visibility),
                        contentDescription = null,
                        tint = Orange
                    )
                else Icon(
                    painter = painterResource(R.drawable.ic_visibility_off),
                    contentDescription = null, tint = Orange
                )
            }
        }
    )
}

@Composable
fun UnderlinedText(text: String, size: TextUnit, color: Color,textAlign: TextAlign) {
    Text(
        text = text,
        fontSize = size,
        color = color,
        fontFamily = FontFamily(
            Font(R.font.poppins_medium)
        ), textDecoration = TextDecoration.Underline,
        modifier = Modifier.fillMaxWidth(),
        textAlign = textAlign
    )
}

@Composable
fun CustomButton(text: String) {
    Button(
        onClick = {},
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Orange)
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            color = Color.White,
            fontFamily = FontFamily(
                Font(R.font.poppins_medium)
            ),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun GoogleButton() {
    Button(
        onClick = {},
        shape = RoundedCornerShape(24.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White)

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.google),
                contentDescription = null,
                alignment = Alignment.CenterStart,
                modifier = Modifier.size(30.dp)
            )

            Text(
                text = "Sign in with google",
                fontSize = 12.sp,
                color = BlueGray,
                fontFamily = FontFamily(
                    Font(R.font.poppins_medium)
                ),
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,

                )
        }

    }
}

