import { Injectable, OnInit } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { Personaje } from './Personaje';
import { FormControl, FormGroup } from '@angular/forms';
import { fail } from 'assert';

@Injectable({
  providedIn: 'root'
})

export class DataService implements OnInit {

  // Datos de los personajes
  //--------------------------------------------------------------------------

  private raza = new BehaviorSubject<string>('No raza')
  raza$ = this.raza.asObservable()
  actualizarRaza(newRaza :string){
    this.raza.next(newRaza)
  }
  obtenerRaza(): string{
    return this.raza.value
  }

  //--------------------------------------------------------------------------

  private fomrEstadisticas = new BehaviorSubject<FormGroup>(new FormGroup({
    vit: new FormControl(10),
    str: new FormControl(10),
    des: new FormControl(10),
    res: new FormControl(10),
    arc: new FormControl(10)
  }));
  fomrEstadisticas$ = this.fomrEstadisticas.asObservable()
  actualizarFormEstadisticas(formNew: FormGroup){
    this.fomrEstadisticas.next(formNew)
  }
  obtenerFormularioEstadisticas(): FormGroup {
    return this.fomrEstadisticas.getValue()
  }

  //--------------------------------------------------------------------------

  private habilidad = new BehaviorSubject<string>('No hab')
  habilidad$ = this.habilidad.asObservable()
  actualizarHabilidad(newHab :string){
    this.habilidad.next(newHab)
  }
  obtenerHabilidad(){
    console.log(this.habilidad)
    return this.habilidad.value
  }

  //--------------------------------------------------------------------------

  private fomrNombre = new BehaviorSubject<FormGroup>(new FormGroup({
    nombre: new FormControl('Sin Nombre'),
    edad: new FormControl(0)
  }));
  fomrNombre$ = this.fomrNombre.asObservable()
  actualizarFormNombre(newFormN: FormGroup){
    this.fomrNombre.next(newFormN)
  } 
  obtenerFormularioNombre(): FormGroup {
    return this.fomrNombre.value
    
  }

  //--------------------------------------------------------------------------


  //Creacion e inserccion de nuevos personajes

  protected id: number = 0

  ngOnInit() {
    this.id = this.getAllPersonajes().length  ;
  
  }

  private personajeList = new BehaviorSubject<Personaje[]>([
     {
      id: 0,
      nombre: 'Ajaks',
      edad : 1000,
      raza: 'Elfo',
      estadisticas: [
        10,
        10,
        10,
        10,
        10
      ],
      habilidad: 'Sigilo'
    },
    {
      id: 1,
      nombre: 'Ajaks',
      edad : 1000,
      raza: 'Elfo',
      estadisticas: [
        10,
        10,
        10,
        10,
        10
      ],
      habilidad: 'Sigilo'
    },

  ])

  personajelist$ = this.personajeList.asObservable()

 

  getAllPersonajes():Personaje[] {
    return this.personajeList.getValue()
  }

  getPersonajeById(id: number): Personaje | undefined {
    return this.personajeList.getValue().find((personaje) => personaje.id === id);
  }

  AgregarPersonaje( personaje:Personaje){
    const personajes = this.getAllPersonajes()
    personajes.push(personaje)
    this.personajeList.next([...personajes])
    console.log(personajes.values)
  }

  //--------------------------------------------------------------------------

  // Seleccion navbarPersonaje

  private mensajeCrearPersonaje = new BehaviorSubject<string>('Valor Inicial')
  private botonSeleccionado = new BehaviorSubject<string>("raza")
  private terminado = new BehaviorSubject<boolean>(false)
 
  botonSeleccionado$ = this.botonSeleccionado.asObservable()
  mensajeCrearPersonaje$ = this.mensajeCrearPersonaje.asObservable()
  terminado$ = this.terminado.asObservable()

  constructor() { }

  ActualizarMensajePersonaje(newMensaje: string){
    this.mensajeCrearPersonaje.next(newMensaje)
    this.botonSeleccionado.next(newMensaje)
  }

  PersonajeTerminado(cond:boolean){
    this.terminado.next(cond)
  }



 
 
}
