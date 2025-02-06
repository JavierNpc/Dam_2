import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';

export const routes: Routes = [

    {
        path: '',
        component: HomeComponent,
        title: 'Home page',
    },
    /* {
        path: '/nav',
        component: NavbarComponent,
        title: 'navBar',
    }, */

];
