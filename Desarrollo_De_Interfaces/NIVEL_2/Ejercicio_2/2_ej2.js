var cond = 0

function cambiarColor(){
  
   if(cond == 0){
      document.getElementById("div1").style.backgroundColor = "red"
      cond = 1
   }else{
      document.getElementById("div1").style.backgroundColor = "blue"
      cond = 0
   }

  

}