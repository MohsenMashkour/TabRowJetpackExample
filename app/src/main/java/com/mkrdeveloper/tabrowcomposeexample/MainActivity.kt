package com.mkrdeveloper.tabrowcomposeexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mkrdeveloper.tabrowcomposeexample.ui.theme.TabItem
import com.mkrdeveloper.tabrowcomposeexample.ui.theme.TabRowComposeExampleTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tabItems = listOf(
            TabItem(
                title = "Home",
                selectedIcon = Icons.Filled.Home,
                unSelectedIcon = Icons.Outlined.Home
            ),
            TabItem(
                title = "Profile",
                selectedIcon = Icons.Filled.Face,
                unSelectedIcon = Icons.Outlined.Face
            ),
            TabItem(
                title = "Favorite",
                selectedIcon = Icons.Filled.Favorite,
                unSelectedIcon = Icons.Outlined.FavoriteBorder
            ),
        )
        setContent {
            TabRowComposeExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var selectedTabIndex by remember {
                        mutableIntStateOf(0)
                    }
                    val pagerState = rememberPagerState {
                        tabItems.size
                    }
                    LaunchedEffect(key1 = selectedTabIndex) {
                        pagerState.animateScrollToPage(selectedTabIndex)
                    }
                    LaunchedEffect(
                        key1 = pagerState.currentPage,
                        key2 = pagerState.isScrollInProgress
                    ) {
                        if (!pagerState.isScrollInProgress) {
                            selectedTabIndex = pagerState.currentPage
                        }

                    }
                    Column(Modifier.fillMaxSize()) {
                        TabRow(selectedTabIndex = selectedTabIndex) {

                            tabItems.forEachIndexed { index, item ->
                                Tab(
                                    selected = index == selectedTabIndex,
                                    onClick = {
                                        selectedTabIndex = index
                                    },
                                    text = {
                                        Text(text = item.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (index == selectedTabIndex) {
                                                item.selectedIcon
                                            } else item.unSelectedIcon,
                                            contentDescription = item.title
                                        )
                                    },
                                    selectedContentColor = Color.Black,
                                   /* unselectedContentColor = Color.Magenta*/
                                )
                            }
                        }
                        HorizontalPager(
                            state = pagerState,
                            Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) { index ->

                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                    Icon(
                                        imageVector = tabItems[index].unSelectedIcon,
                                        contentDescription = "",Modifier.size(152.dp)
                                    )
                                    Text(text = tabItems[index].title, fontSize = 52.sp)
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}


