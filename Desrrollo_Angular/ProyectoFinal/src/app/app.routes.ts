import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CrearPersonajeComponent } from './crear-personaje/crear-personaje.component';
import { PersonajeRazaComponent } from './personaje-raza/personaje-raza.component';

export const routes: Routes = [

    {
        path: '',
        component: HomeComponent,
        title: 'Home page',
    },
    {
        path: 'crearpersonaje',
        component: CrearPersonajeComponent,
        title: 'Crear Personaje',
    },
    {
        path: 'crearpersonaje/raza',
        component: PersonajeRazaComponent,
        title: 'Crear Personaje',
    },
    {
        path: 'crearpersonaje/estadisticas',
        component: PersonajeRazaComponent,
        title: 'Crear Personaje',
    },
    {
        path: 'crearpersonaje/habilidades',
        component: PersonajeRazaComponent,
        title: 'Crear Personaje',
    },
    {
        path: 'crearpersonaje/nombre',
        component: PersonajeRazaComponent,
        title: 'Crear Personaje',
    },
   
    

];
