import { Component, inject } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { ListaDePersonajesComponent } from "../lista-de-personajes/lista-de-personajes.component";
import { Personaje } from '../Personaje';
import { DataService } from '../data.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-visor-de-personajes',
  imports: [NavbarComponent, ListaDePersonajesComponent, CommonModule],
  templateUrl: './visor-de-personajes.component.html',
  styleUrl: './visor-de-personajes.component.css'
})
export class VisorDePersonajesComponent {
  PersonajeList: Personaje[] = [];
  dataService: DataService = inject(DataService);
  filteredPersonajeList: Personaje[] = [];
  PersonajeLocation: any;
  constructor() {
    this.PersonajeList = this.dataService.getAllPersonajes();
    this.filteredPersonajeList = this.PersonajeList;
  }

  filterResults(text: string) {
    if (!text) {
      this.filteredPersonajeList = this.PersonajeList;
      return;
    }
    this.filteredPersonajeList = this.PersonajeList.filter((PersonajeLocation) =>
      PersonajeLocation?.nombre.toLowerCase().includes(text.toLowerCase()),
    );
  }

  

}

