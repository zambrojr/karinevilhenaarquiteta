<template>
    <div ref="container">              
        <img ref="logoGame" src='\./images/space/gameStart.png' style="position:absolute; top:20%; left:30%">
        <img ref="gameOver" src='\./images/space/gameOver.png' hidden="true" style="position:absolute; top:20%; left:15%">
        <div v-bind:style="baseStyle"></div>        
    </div>
</template>

<script>
    /* global Vue*/

    import Enemy from './_enemy.vue'
    import Missil from './_fire.vue'
    
    
export default {
    props: {
        //rows: Array,
        //type: String,
    },
    
    data() {
        return {
            lifes:3,
            tempoAparicaoInimigo: 1000,
            base:{
                size:5,
                left:10,
                top:0,
                velocidadeBase:20,
                height:1,
                width:50
            },
        }
    },
    
    methods:{


        gameOver(){
            this.$refs.gameOver.hidden=false;
            var tis = this;
            setTimeout(function(){ tis.$emit('over', tis); }, 4000);
            
        },

        insertEnemy(){
            var ComponentClass = Vue.extend(Enemy)
            var instance = new ComponentClass({
                propsData: { ground: this.base.top }
                });
            instance.$on('objetivoCompleto', this.atingido);
            instance.$mount() // pass nothing
            this.$refs.container.appendChild(instance.$el);
        },

        
        atingido(instance){
            this.$refs.container.removeChild(instance.$el);
            instance.$destroy();
            this.lifes--;
        },        
                

        desativaMissel(instance){
            this.$refs.container.removeChild(instance.$el);
            instance.$destroy();
        },    


        initGame(){
            var tis = this;
            var interval = setInterval(function(){
                    if( tis.lifes <=0 ) {clearInterval(interval); tis.gameOver();}
                    tis.insertEnemy();
                }, this.tempoAparicaoInimigo);                
        },
        
        
        fire(){
            var ComponentClass = Vue.extend(Missil)
            var instance = new ComponentClass();
            instance.$on('objetivoCompleto', this.desativaMissel);
            instance.setLeftPosition(this.base.left);
             
            //instance.p
            instance.$mount() // pass nothing
            this.$refs.container.appendChild(instance.$el);            
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



           /* acertou(){
                return ( (this.ball.top + this.ball.height)  >= this.base.top && (this.ball.top + this.ball.height) <= this.base.top + this.base.height )
                        && (this.ball.left >= this.base.left && this.ball.left <= this.base.left + this.base.width)
            },
                */
    },
    
    /*  */
    mounted() {
        this.base.top = window.innerHeight - 20;
        window.addEventListener("keypress", e => {
            if(String.fromCharCode(e.keyCode) == '6')this.base.left = this.base.left+ (1 * this.base.velocidadeBase);
            if(String.fromCharCode(e.keyCode) == '4')this.base.left = this.base.left- (1 * this.base.velocidadeBase);
            //if(e.keyCode == 32) this.fire();
            if(e.keyCode == 97) this.fire();
        });
        window.addEventListener("mousemove", e => { this.base.left = e.clientX; });                 
        var tis = this;
        setTimeout(function(){ tis.$refs.logoGame.hidden=true; tis.initGame(); }, 4000);
    },      
    
}
</script>

