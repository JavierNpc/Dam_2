function mover_reloj(){
   var momentoActual = new Date()

   var hora = momentoActual.getHours()
   var min = momentoActual.getMinutes()
   var seg = momentoActual.getSeconds()
   var horaimprimible = hora+" "+min+" "+seg
   
   document.form_reloj.reloj.value = horaimprimible
    
   setTimeout("mover_reloj()", 1000)

}
