var alturaIn = 10
var alturaFin = 10

function ampliar(){
   if (alturaIn <= 100){
      alturaIn = parseInt(document.getElementById("div1").style.height) +10
      alturaFin = String(alturaIn +"px")
      document.getElementById("div1").style.height = alturaFin
   }else{
      alert("No se puede aumentar mas de tamaño")
   }
}

function reducir(){
   if (alturaIn >= 20){
      alturaIn = parseInt(document.getElementById("div1").style.height) -10
      alturaFin = String(alturaIn +"px")
      document.getElementById("div1").style.height = alturaFin
   }else{
      alert("No se puede aumentar mas de tamaño")
   }
      
}


