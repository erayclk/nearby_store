package com.example.nearby_store.Activites.Dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nearby_store.R

@Composable
@Preview
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(325.dp)
    ) {
        // Mavi arka plan kutusu
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(colorResource(R.color.blue))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp, end = 15.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(R.drawable.profile),
                        contentDescription = ""
                    )
                }

                Box {
                    Image(
                        painter = painterResource(R.drawable.building),
                        contentDescription = ""
                    )
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "Nearby Store",
                                color = colorResource(R.color.white),
                                fontSize = 20.sp,
                                modifier = Modifier.padding(top = 25.dp)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "What are you looking for?",
                                color = colorResource(R.color.white),
                                fontSize = 25.sp,
                                fontWeight = FontWeight.ExtraBold,
                                modifier = Modifier.padding(top = 0.dp)
                            )
                        }
                    }
                }
            }
        }

        // Card: Üste doğru bindirilmiş olarak gösteriliyor
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 225.dp) // 280 - 55 = 225
                .size(width = 300.dp, height = 100.dp)
                .background(color = Color.White, shape = RoundedCornerShape(15.dp))
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column (modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),){
                    Row (modifier = Modifier.padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Image(
                            painter = painterResource(R.drawable.wallet),
                            contentDescription = ""
                        )
                        Text(
                            text = "Wallet",
                            fontSize = 14.sp,
                            fontWeight =  FontWeight.SemiBold,
                            style = TextStyle(textDecoration = TextDecoration.Underline ),
                            modifier = Modifier.padding(start = 10.dp,end= 10.dp)

                        )

                        Image(
                            painter = painterResource(R.drawable.arrow),
                            contentDescription = ""

                        )
                    }

                    HorizontalDivider(thickness = 1.dp, modifier = Modifier.padding(end =8.dp, start = 8.dp))

                    Row (modifier = Modifier.padding(15.dp),
                        verticalAlignment = Alignment.CenterVertically
                        ){

                        Image(
                            painter = painterResource(R.drawable.medal),
                            contentDescription = ""
                        )
                        Text(
                            text = "Reward",
                            fontSize = 14.sp,
                            fontWeight =  FontWeight.SemiBold,
                            style = TextStyle(textDecoration = TextDecoration.Underline ),
                            modifier = Modifier.padding(start = 10.dp,end= 10.dp)

                        )

                        Image(
                            painter = painterResource(R.drawable.arrow),
                            contentDescription = ""

                        )
                    }

                }

                VerticalDivider(thickness = 1.dp, modifier = Modifier.padding(top =8.dp, bottom = 8.dp))

                Column (modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 20.dp)
                    .weight(1f),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center


                    ){
                    Text(text = "Balance",fontSize = 14.sp, fontWeight = FontWeight.SemiBold,
                        style = TextStyle(textDecoration = TextDecoration.Underline ))

                    Spacer( modifier = Modifier.padding(3.dp))
                    Row {
                        Text(text = "150 tl ",fontSize = 16.sp, fontWeight = FontWeight.ExtraBold,
                            style = TextStyle(textDecoration = TextDecoration.Underline ))

                        Image(
                            painter = painterResource(R.drawable.arrow),
                            contentDescription = "")
                    }


                }

            }

        }

    }
}


