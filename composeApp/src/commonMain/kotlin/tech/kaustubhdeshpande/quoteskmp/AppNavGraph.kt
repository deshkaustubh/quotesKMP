// kotlin
package tech.kaustubhdeshpande.quoteskmp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import tech.kaustubhdeshpande.quoteskmp.view.components.BottomNavigationBar
import tech.kaustubhdeshpande.quoteskmp.view.screens.ExploreScreen
import tech.kaustubhdeshpande.quoteskmp.view.screens.HomeScreen
import tech.kaustubhdeshpande.quoteskmp.view.screens.SavedScreen

@Composable
fun AppNavGraph(
    modifier: Modifier = Modifier,
    onToggleTheme: () -> Unit
) {
    val navController = rememberNavController()
    val favViewModel = remember { FavouriteViewModel() }

    Scaffold(
        bottomBar = {
            val backStackEntry = navController.currentBackStackEntryAsState().value
            val hierarchy = backStackEntry?.destination?.hierarchy

            val isHomeSelected = hierarchy?.any { it.hasRoute<QuotesRoute.Home>() } == true
            val isExploreSelected = hierarchy?.any { it.hasRoute<QuotesRoute.Explore>() } == true
            val isSavedSelected = hierarchy?.any { it.hasRoute<QuotesRoute.Saved>() } == true

            BottomNavigationBar(
                isHomeSelected = isHomeSelected,
                isExploreSelected = isExploreSelected,
                isSavedSelected = isSavedSelected,
                onNavigate = { route ->
                    navController.navigate(route) {
                        val startId = navController.graph.findStartDestination().id
                        popUpTo(startId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = QuotesRoute.Home,
            modifier = modifier.padding(innerPadding)
        ) {
            composable<QuotesRoute.Home> {
                HomeScreen(
                    onNavigateToExplore = { category ->
                        navController.navigate(QuotesRoute.Explore(category)) {
                            val startId = navController.graph.findStartDestination().id
                            popUpTo(startId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    favViewModel = favViewModel,
                    onToggleTheme = onToggleTheme
                )
            }
            composable<QuotesRoute.Explore> { entry ->
                val route: QuotesRoute.Explore = entry.toRoute()
                ExploreScreen(
                    initialCategoryName = route.category,
                    favViewModel = favViewModel
                )
            }
            composable<QuotesRoute.Saved> {
                SavedScreen(favViewModel = favViewModel)
            }
        }
    }
}