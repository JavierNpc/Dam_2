import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { CrearPersonajeComponent } from './crear-personaje/crear-personaje.component';
import { PersonajeRazaComponent } from './personaje-raza/personaje-raza.component';
import { ListaDePersonajesComponent } from './lista-de-personajes/lista-de-personajes.component';

export const routes: Routes = [

    {
        path: '',
        component: HomeComponent,
        title: 'Home page',
    },
    {
        path: 'listapersonajes',
        component: ListaDePersonajesComponent,
        title: 'Listado de los personajes',
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
