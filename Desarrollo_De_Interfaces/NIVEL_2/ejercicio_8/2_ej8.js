function valorVacio(){
   //alert(document.getElementById("texto1").textContent)

   if ( document.getElementById("texto1").value === "") {
     document.getElementById("texto1").setAttribute("style","border-color: red;")
   }else{
      document.getElementById("texto1").setAttribute("style","border-color: green;")
   }
      
   setTimeout(valorVacio , 100)
}

