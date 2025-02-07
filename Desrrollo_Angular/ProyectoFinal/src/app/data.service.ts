import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})

export class DataService {

  private mensajeCrearPersonaje = new BehaviorSubject<string>('Valor Inicial')
 
  mensajeCrearPersonaje$ = this.mensajeCrearPersonaje.asObservable()

  constructor() { }

  ActualizarMensajePersonaje(newMensaje: string){
    this.mensajeCrearPersonaje.next(newMensaje)
  }

 
 
}
