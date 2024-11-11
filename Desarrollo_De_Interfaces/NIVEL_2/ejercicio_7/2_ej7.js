
var num = 0

function cambiarImagen(){
   document.getElementById("imagen1").src = 'asta2.jpg'
   document.getElementById("imagen1").style = "max-width: 160px;"
   
   
}

function cambiarImagen2(){
   document.getElementById("imagen1").src = 'astas blclover.jpeg'
   document.getElementById("num").innerHTML = ++num
 
      
}


var val = setInterval(cambiarImagen2,3000).getElementById
alert(val)
clearInterval(val)

