package com.example.newsapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.uiStates.OnboardingEvent
import kotlinx.coroutines.launch

@Composable
fun Pager(modifier: Modifier = Modifier, pagerState: PagerState,  onEvent: suspend (OnboardingEvent) -> Unit) {


    HorizontalPager(
        state = pagerState
    ) { page: Int ->
        Column {
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Indicators(pagerState = pagerState)

            }

            Spacer(Modifier.height(10.dp))
            Buttons(pagerState = pagerState, onEvent)
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
fun Indicators(pagerState: PagerState) {
    repeat(pagerState.pageCount) { index ->
        val indicatorColor =
            if (index == pagerState.currentPage) MaterialTheme.colorScheme.primary else Color.LightGray

        Box(
            modifier = Modifier
                .padding(4.dp)
                .clip(CircleShape)
                .size(16.dp)
                .background(color = indicatorColor)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Buttons(pagerState: PagerState, onEvent: suspend (OnboardingEvent) -> Unit) {

    Row(
        modifier = Modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        val isPrevButtonEnabled = when (pagerState.currentPage) {
            1 -> true
            else -> false
        }

        val isNextButtonEnabled = when (pagerState.currentPage) {
            0, 1 -> true
            else -> false
        }

        val isGetStartedButtonEnabled = when (pagerState.currentPage) {
            2 -> true
            else -> false
        }
        val coroutineScope = rememberCoroutineScope()
        if (isPrevButtonEnabled) {
            IconButton(onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            }) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowLeft, contentDescription = null)

            }
        }


        if (isNextButtonEnabled) {
            IconButton(onClick = {
                coroutineScope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
                }
            }) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)

            }
        }

        if (isGetStartedButtonEnabled) {
            Button(
                modifier = Modifier
                    .wrapContentHeight()
                    .background(MaterialTheme.colorScheme.primary)
                    .clip(CircleShape),
                onClick = {
                    coroutineScope.launch {
                        onEvent(OnboardingEvent.SaveAppEntry)
                    }
                }) {
                Text("Get Started")
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewPager() {
    NewsAppTheme {
        Pager(pagerState = rememberPagerState(initialPage = 2) { 3 }, onEvent = {})
    }
}