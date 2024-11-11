var cond = true

function añadirElemento(){
   const nuevoElemento = document.createElement("li") 
   var texto = document.getElementById("caja").value
   nuevoElemento.textContent =  texto
   document.getElementById("lista").appendChild(nuevoElemento)
   contarLineas()
   
}

function contarLineas(){
   const numL = document.getElementById("lista").childElementCount
   document.getElementById("lineas").innerHTML = "Nº de lineas = "+numL
}

function borrar(id){
   id.remove()
   contarLineas()
}

function borrarAtributo(id){

   const lista = document.getElementById("lista")
   const nodeList = lista.querySelectorAll("li")
   for (let i = 0; i < nodeList.length; i++) {
      nodeList[i].style.color = "black"
      nodeList[i].removeAttribute( "onmousedown" , "borrar(this)");
   }
    

}


function eliminarElemento(){

 
   document.getElementById("par").innerHTML = "Selecciona un elemento para borrarlo"
   const lista = document.getElementById("lista")
   const nodeList = lista.querySelectorAll("li")
   for (let i = 0; i < nodeList.length; i++) {
      nodeList[i].style.color = "red"
      nodeList[i].setAttribute( "onmousedown" , "borrar(this)");
      nodeList[i].setAttribute( "onmouseup" , "borrarAtributo(this)");
   }
  
}