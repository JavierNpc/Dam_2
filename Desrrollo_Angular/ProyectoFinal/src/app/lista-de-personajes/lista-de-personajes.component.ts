import { Component, Input } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { Personaje } from '../Personaje';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-lista-de-personajes',
  imports: [CommonModule],
  templateUrl: './lista-de-personajes.component.html',
  styleUrl: './lista-de-personajes.component.css'
})
export class ListaDePersonajesComponent {
  @Input() Personaje!: Personaje;
}
