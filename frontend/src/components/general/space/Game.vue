<template>
    <div ref="container">      
Game
        <div v-bind:style="baseStyle"></div>
        <div v-bind:style="ballStyle"></div>        
        
    </div>
</template>

<script>
    
    
export default {
    props: {
        //rows: Array,
        //type: String,
    },
    
    data() {
        return {
            lifes:3,
            base:{
                size:5,
                left:10,
                top:0,
                velocidadeBase:20,
                height:1,
                width:50
            },
            ball:{
                velocidade:9.0,
                left:0,
                top:0,
                directionTop:true,
                directionLeft:true,
                height:5,
                width:5
            },
        }
    },
    
    methods:{

        movimentaBall(){
            this.ball.left      = this.ball.directionLeft   ? this.ball.left+(1 * this.ball.velocidade) : this.ball.left-(1 * this.ball.velocidade);
            this.ball.top       = this.ball.directionTop    ? this.ball.top +(1 * this.ball.velocidade) : this.ball.top -(1 * this.ball.velocidade);
            if(this.ball.top    >= window.innerHeight   || this.ball.top    <= 0)   this.ball.directionTop  = !this.ball.directionTop;
            if(this.ball.left   >= window.innerWidth    || this.ball.left   <= 0)   this.ball.directionLeft = !this.ball.directionLeft;
            if(this.acertou) this.ball.directionTop = !this.ball.directionTop;
            
            let element = document.elementFromPoint(this.ball.left, this.ball.top);
            if(element != null && element.className.indexOf("form-control") !== -1) {
            //if(element.closest(".conteudo") != null){
                element.parentNode.removeChild(element);
                //this.ball.directionLeft = !this.ball.directionLeft;
                this.ball.directionTop = !this.ball.directionTop;
            }
        },
        

        initBall(){
            var tis = this;
            var interval = setInterval(function(){
                    if( tis.lifes <=0 ) clearInterval(interval);
                    tis.movimentaBall();
                }, 100);
                

        },
        
        
        fire(){
            
        }

    },
    
    computed: {

            baseStyle(){
                return {
                    fontSize: this.base.size + 'px' , 
                    position: 'fixed',
                    left: this.base.left + 'px',
                    top: this.base.top + 'px',
                    height: this.base.height + 'px' , 
                    width: this.base.width + 'px'      ,
                    background: 'BLACK'
                }
            },

            ballStyle(){
                return {
                    position: 'fixed',
                    left: this.ball.left + 'px',
                    top: this.ball.top + 'px',
                    height: this.ball.height + 'px' , 
                    width: this.ball.width + 'px' , 
                    'border-radius': '50%',
                    background: 'GREEN'
                }
            },

            acertou(){
                return ( (this.ball.top + this.ball.height)  >= this.base.top && (this.ball.top + this.ball.height) <= this.base.top + this.base.height )
                        && (this.ball.left >= this.base.left && this.ball.left <= this.base.left + this.base.width)
            },

    },
    
    /*  */
    mounted() {
        this.base.top = window.innerHeight - 20;
        window.addEventListener("keypress", e => {
            
            if(String.fromCharCode(e.keyCode) == '6')this.base.left = this.base.left+ (1 * this.base.velocidadeBase);
            if(String.fromCharCode(e.keyCode) == '4')this.base.left = this.base.left- (1 * this.base.velocidadeBase);
            
            if(e.keyCode == 32) this.fire();
            
             console.log(e.keyCode);
        });
                
        window.addEventListener("mousemove", e => { this.base.left = e.clientX; });          
                
        this.initBall();

    },      
    
}
</script>

