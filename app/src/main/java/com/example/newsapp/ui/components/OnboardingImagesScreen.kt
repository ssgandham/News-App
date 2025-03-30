package com.example.newsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.uiStates.Page
import com.example.newsapp.ui.uiStates.pages
import com.example.newsapp.ui.utils.Constants

@Composable
fun OnboardingImagesScreen(modifier: Modifier = Modifier, pages: List<Page>) {

    val pagerState = rememberPagerState(pageCount = { Constants.THREE })
    val image = fetchImage(pagerState)
    val page = pages[pagerState.currentPage]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding(),
//        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.60f),
            painter = painterResource(image), contentDescription = null,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = page.title,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
        )
        Text(
            text = page.title,
            style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.weight(1f))
        Pager(modifier = Modifier, pagerState = pagerState)
    }
}

@Composable
private fun fetchImage(pagerState: PagerState): Int {
    val image = when (pagerState.currentPage) {
        0 -> R.drawable.onboarding1
        1 -> R.drawable.onboarding2
        else -> R.drawable.onboarding3
    }
    return image
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboardingImagesScreen() {
    NewsAppTheme {
        OnboardingImagesScreen(modifier = Modifier.fillMaxSize(), pages = pages)
    }
}