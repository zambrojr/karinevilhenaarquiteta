<template>
  <div id="app">

      <button @click="iniciarOpcaoGravacao" >Gravar proposta</button>
      <div v-if="falarProposta">

        <h2>Record</h2>
        <p>
          <button @click="record" :disabled="gravando" id=record>Iniciar</button>
          <button @click="pauseRecording" :disabled="!gravando" id=record>{{pauseButton}}</button>
          <button @click="stopRecord" :disabled="!gravando" id=stopRecord>Stop</button>
          <img style="width:25px" v-bind:class="{ 'blink-image': !pausado }" v-show="gravando" src="/images/blink.png">
        </p>
        <p>
          <audio controls ref="recordedAudio"></audio>
        </p>
        <button @click="upload">Upload</button>
      </div>
      <div class="pt-5">
          <b-table class="small text-left"  responsive :fields="fields"  :small=true :striped=true :items="listaPropostas">

                <template v-slot:cell(texto_transcrito)="row">
                    <b-form-textarea :value="row.item.texto_transcrito" rows="3" max-rows="6" ></b-form-textarea>                                      
                </template>   

                <template v-slot:cell(audio)="row">
                    <b-icon icon="play-fill" variant="success" font-scale="3" v-show="!row.item._ativo"  @click="streamProposta(row.item)"></b-icon>
                    <audio controls preload="none" v-show="row.item._ativo" :ref="playerName(row.item.id)" autoplay=false></audio>
                </template>   
          </b-table>    
      </div>

  </div>



</template>

<script>

/* global webkitSpeechRecognition*/
/* global SpeechRecognition*/

//import Recorder from "./lib/Recorder"
import Recorder from "./lib/recorder" 

export default {
  name: 'App',
  components: {   
  },

      data() {
          return {
            rec:{},
            recognition:{},
            textoTranscrito:'',
            blob:{},
            gumStream:{},
            pauseButton:'Pause',
            gravando:false,
            pausado:false,
            falarProposta:false,

            /* */
            listaPropostas:[],
            fields: [
                    { key: 'size', label: 'Tamanho(bytes)',  sortable: true  },
                    { key: 'audio', label: 'Audio',  sortable: true  },
                    { key: 'status', label: 'Status',  sortable: true  },                    
                    { key: 'texto_transcrito', label: 'Texto Transcrito',  sortable: false }     
              ],                   
          }
      },  

      computed:{
        //gravando() { return (this.rec instanceof Recorder) && this.rec.state == "recording"; },
        gravandos() { return (this.rec instanceof Recorder) ? this.rec.state : ""; }
      },

      methods:{


        /* */
        iniciarOpcaoGravacao(){            
            navigator.mediaDevices.getUserMedia({audio:true}).then(stream => {this.handlerFunction(stream)})
        },


        /* */
        streamAudio(recordedAudio, idAudio){

            const params = new FormData();
            params.append("id", idAudio );    


                this.$api.projetos.propostas.streamAudio(params).then((response) => {
                    if(response.status == 200){
                      //let blob = new Blob([response.data],{type:'audio/mpeg-3'});
                      let blob = new Blob([response.data],{type:'audio/webm'});
                      this.$refs[recordedAudio].src = URL.createObjectURL(blob);
                      this.$refs[recordedAudio].controls=true;
                      this.$refs[recordedAudio].autoplay=true;                      
                    }
                })
                .catch(error => {
                  console.log(error);
                });  
        },

        initRecognition(){

              this.recognition = new webkitSpeechRecognition() || new SpeechRecognition();
              this.recognition.interimResults = false;
              this.recognition.lang = "pt-BR";
              this.recognition.continuous = true;
              //var objthis = this;
              this.recognition.onend = function() { 
                
              }

        },

          /* */
          transcreveAudio(event){
              for (let i = event.resultIndex; i < event.results.length; i++) {
                    if (event.results[i].isFinal) {
                      const content = event.results[i][0].transcript.trim();
                      console.log(content);
                      this.textoTranscrito = this.textoTranscrito+content+"\r\n";
                    }
              }
          },


          /* */
         handlerFunction(stream) {

            this.falarProposta=true;
            var audioContext = new AudioContext();
            var input = audioContext.createMediaStreamSource(stream);

            this.gumStream = stream;
            this.rec         = new Recorder(input,{numChannels:1});

            this.initRecognition();
          },

          /* */
          record(){
              this.recognition.start();
              this.recognition.onresult = this.transcreveAudio;
              
              this.rec.record();
              this.textoTranscrito = '';
              this.gravando = true;
              this.pausado=false;
              console.log(this.rec.state)
          },

          /* */
          pauseRecording(){
                console.log("pauseButton clicked rec.recording=",this.rec.recording );
                if (this.rec.recording){
                  //pause
                  this.rec.stop();
                  this.pauseButton="Resume";
                  this.pausado=true;
                }else{
                  //resume
                  this.rec.record()
                  this.pauseButton="Pause";
                  this.pausado=false;
                }
          },

          /* */
          stopRecord() {
              console.log("I was clicked")
              //this.recognition.stop();
              
              this.rec.stop();
              this.gravando = false;
              this.gumStream.getAudioTracks()[0].stop();
              console.log(this.textoTranscrito)
              this.rec.exportWAV(this.getAudio);
          },


        /* */
        getAudio(blob){

            this.blob = blob;
            this.$refs.recordedAudio.src = URL.createObjectURL(blob);
            this.$refs.recordedAudio.controls=true;
            this.$refs.recordedAudio.autoplay=true;
        }, 

        /* */
        upload(){

            const params = new FormData();
            params.append("arquivo", this.blob );    
            params.append("transcrito", this.textoTranscrito );   

            this.$api.projetos.propostas.novaProposta(params).then((response) => {
                    if(response.status == 200){
                        this.modalUpload = false;
                        this.$bvToast.toast('Salvo com sucesso!', { title: 'Cadastro de anexo',   variant: 'success',    toaster:'b-toaster-top-center'    });
                        this.listaanexos();
                    }
                })
                .catch(error => {
                  console.log(error);
                });  

        },          

        /* */
        getListaPropostas(){
                this.$api.projetos.propostas.listaPropostas({}).then((response) => {
                    if(response.status == 200){
                      this.listaPropostas = [];
                      response.data.forEach(x=>{
                        this.$set(x,"_ativo",false);
                          this.listaPropostas.push(x);
                      })
                      
                    }
                })
                .catch(error => {
                  console.log(error);
                });  
        },


        /* */
        playerName(id){
          return "player_"+id
        },

        /* */
        streamProposta(obj){
          obj._ativo=true;
          var ref= this.playerName(obj.id);
          this.streamAudio(ref, obj.id);
          console.log(ref)

        }



          
      },

  mounted(){
    this.getListaPropostas();
  }

}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.blink-image {
    -moz-animation: blink normal 2s infinite ease-in-out; /* Firefox */
    -webkit-animation: blink normal 2s infinite ease-in-out; /* Webkit */
    -ms-animation: blink normal 2s infinite ease-in-out; /* IE */
    animation: blink normal 2s infinite ease-in-out; /* Opera and prob css3 final iteration */
}
 
@-moz-keyframes blink {
    0% {
        opacity:1;
    }
    50% {
        opacity:0;
    }
    100% {
        opacity:1;
    }
} 
 
@-webkit-keyframes blink {
    0% {
        opacity:1;
    }
    50% {
        opacity:0;
    }
    100% {
        opacity:1;
    }
}
/* IE */
@-ms-keyframes blink {
    0% {
        opacity:1;
    }
    50% {
        opacity:0;
    }
    100% {
        opacity:1;
    }
} 
/* Opera and prob css3 final iteration */
@keyframes blink {
    0% {
        opacity:1;
    }
    50% {
        opacity:0;
    }
    100% {
        opacity:1;
    }
} 
</style>
