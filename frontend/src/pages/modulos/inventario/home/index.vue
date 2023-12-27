<template>

    <div>


        <section class="statistics section-padding">
                <div class="container-fluid">
                    <div class="row d-flex">
                        
                        <div class="col-lg-4 box-income">
                            <div class="card income text-center">                  
                                <b-row>
                                    <b-col>                       
                                        <b-button variant="outline-secondary" @click="changeCamera">Camera</b-button> 
                                        <select v-model="cameraId" id="select">
                                            <option></option>
                                        </select>                                                           
                                    </b-col>
                                </b-row>                                        
                                <b-row class="pt-2">
                                    <b-col>
                                        <video id="video">Video stream not available.</video>
                                    </b-col>
                                </b-row>
                                <b-row class="pt-2">
                                    <b-col>              
                                         <b-button variant="outline-secondary" @click="tiraFoto">Capturar</b-button>                  
                                    </b-col>
                                </b-row>                                        
                            </div>                        

                        </div>       

                    </div>
                </div>

                    <div class="row d-flex">
                        
                        <div class="col-lg-4 box-income">
                            <div class="card income text-center">                  
                                <b-row>
                                    <b-col>   


                                            <b-table stacked="md" selectable select-mode="single"  class="small text-left relatorio" :fields="fieldsParams"  :small=true :striped=true :items="listaProdutos">
                                                <template v-slot:cell(imagem)="row">
                                                    <img class="imgApresInventario" :src="localImage(row.item)">
                                                </template>                      

                                            </b-table>

                                    </b-col>
                                </b-row>                                        
                            </div>     
                        </div>                        

                    </div>   

            </section>

            <canvas id="canvas" v-show="false"></canvas>
            <b-modal v-model="modalFormObjeto" class="mb-5" size="xl" :no-close-on-backdrop=true ok-title="Salvar" cancel-title="Cancelar" @ok="insertAcessorio" :ok-disabled="this.$v.$invalid" >
                <b-row class="pt-2 text-center">
                    <b-col>
                        <b-img :src="photo" alt="The screen capture will appear in this box."></b-img>
                    </b-col>
                </b-row>     
                <div class="row pt-4">
                    <b-form-group class="col-lg-6" label="Descricao:" label-for="input-1" :state="!this.$v.$dirty || this.$v.objInventario.descricao.required" invalid-feedback="Campo obrigatório">
                        <b-form-input  type="text" size="sm" v-model="objInventario.descricao" required placeholder="Descricao"></b-form-input>
                    </b-form-group>
                </div>             
                <div class="row">
                    <b-form-group class="col-lg-6" label="Marca:" label-for="input-1" :state="!this.$v.$dirty || this.$v.objInventario.marca.required" invalid-feedback="Campo obrigatório">
                        <b-form-input  type="text" size="sm" v-model="objInventario.marca" required placeholder="Marca"></b-form-input>
                    </b-form-group>
                </div>      
                <div class="row">
                    <b-form-group class="col-lg-6" label="Quantidade:" label-for="input-1" :state="!this.$v.$dirty || this.$v.objInventario.qtd.required" invalid-feedback="Campo obrigatório">
                        <b-form-input  type="text" size="sm" v-model="objInventario.qtd" required placeholder="Marca"></b-form-input>
                    </b-form-group>
                </div>                                             
            </b-modal>


            
    </div>

</template>

<script>

import { required } from 'vuelidate/lib/validators'

export default {
    
    /* */
    components: {      
     
    }, 


    /* */
    props: {
        
    },   

    /* */
    data() {
        return {
            photo:null,
            blobImage:null,
            modalFormObjeto:false,
            width:280,    // We will scale the photo width to this
            height:0,     // This will be computed based on the input stream

            // |streaming| indicates whether or not we're currently streaming
            // video from the camera. Obviously, we start at false.

            streaming:false,

            // The various HTML elements we need to configure or control. These
            // will be set by the startup() function.

            video:null,
            canvas:null,
            startbutton:null,
            cameraId:null,
            currentStream :null,

            objInventario: {
                descricao:null,
                marca:null,
                qtd:null,
            }            ,

            fieldsParams: [
                    { key: 'imagem', label: 'Produto',  sortable: true },
                    { key: 'descricao', label: 'Qtd Sugerido',  sortable: true },
                    { key: 'marca', label: 'Vlr Sugerido',  sortable: true },
                    { key: 'qtd', label: 'Qtd',  sortable: true },
                    { key: 'acoes', label: '', class:"text-center"},
            ],
            listaProdutos:[]

        }
    },


    computed:{
       
    },


    /* */
    methods: {


        localImage(item){
            return process.env.VUE_APP_BACKEND_URL+"/apintra/orcamento/inventario/streamimage?id="+item.id
        },

        insertAcessorio(){

            const params = new FormData();
            params.append("arquivo", this.blobImage );    
            params.append("descricao", this.objInventario.descricao );   
            params.append("marca", this.objInventario.marca );   
            params.append("qtd", this.objInventario.qtd );   

            this.$api.orcamento.inventario.novo(params).then((response) => {
                    if(response.status == 200){
                        this.modalFormObjeto = false;
                        this.getProdutos();
                    }
                })
                .catch(error => {
                  console.log(error);
                });  


        },

        gotDevices(mediaDevices) {
            const select = document.getElementById('select');
            select.innerHTML = '';
            select.appendChild(document.createElement('option'));
            let count = 1;
            
            mediaDevices.forEach(mediaDevice => {
                if (mediaDevice.kind === 'videoinput') {
                const option = document.createElement('option');
                option.value = mediaDevice.deviceId;
                const label = mediaDevice.label || `Camera ${count++}`;
                const textNode = document.createTextNode(label);
                option.appendChild(textNode);
                select.appendChild(option);
                }
            });
        },     
        
        changeCamera(){

            this.startup();

        },


        stopMediaTracks(stream) {
            stream.getTracks().forEach(track => {
                track.stop();
            });
        },

        loadCamera(){

            if (this.currentStream !== null) {
                this.stopMediaTracks(this.currentStream);
            }

            const videoConstraints = {};
            if (this.cameraId === '' || this.cameraId === null) {
                videoConstraints.facingMode = 'environment';
            } else {
                videoConstraints.deviceId = { exact: this.cameraId };
            }
            
            const constraints = {
                video: videoConstraints,
                audio: false
            };


            navigator.mediaDevices.getUserMedia(constraints)
                .then((stream)=> {
                    this.currentStream = stream;
                    this.video.srcObject = stream;
                    this.video.play();
                })
                //.then(this.gotDevices)
                .catch(function(err) {
                    alert(err);
                });

                this.video.addEventListener('canplay', ()=>{
                if (!this.streaming) {
                    this.height = this.video.videoHeight / (this.video.videoWidth/this.width);
                
                    // Firefox currently has a bug where the height can't be read from
                    // the video, so we will make assumptions if this happens.
                
                    if (isNaN(this.height)) {
                        this.height = this.width / (4/3);
                    }
                
                    this.video.setAttribute('width', this.width);
                    this.video.setAttribute('height', this.height);
                    this.canvas.setAttribute('width', this.width);
                    this.canvas.setAttribute('height', this.height);
                    this.streaming = true;
                }
                }, false);

                
                this.clearphoto();        
        },

        tiraFoto(){
            
            this.takepicture();
        },

        startup() {
                this.video          = document.getElementById('video');
                this.canvas         = document.getElementById('canvas');
                //this.photo          = document.getElementById('photo');
                this.startbutton    = document.getElementById('startbutton');
                navigator.mediaDevices.enumerateDevices().then(this.gotDevices);

                this.loadCamera();
                
        },

        takepicture() {
            var context = this.canvas.getContext('2d');
            if (this.width && this.height) {
                this.canvas.width = this.width;
                this.canvas.height = this.height;
                context.drawImage(this.video, 0, 0, this.width, this.height);
                this.canvas.toBlob((blob)=> {
                    this.blobImage = blob;
                });
                
                var data = this.canvas.toDataURL('image/png');
                this.photo=data;
                //this.resourcePhoto
            this.$nextTick(() => {
                this.modalFormObjeto = true;
                
            });

            } else {
                this.clearphoto();
            }
        },

        clearphoto() {
            var context = this.canvas.getContext('2d');
            context.fillStyle = "#AAA";
            context.fillRect(0, 0, this.canvas.width, this.canvas.height);

            var data = this.canvas.toDataURL('image/png');
            this.photo= data;
            //this.photo.setAttribute('src', data);
        }    ,

        getProdutos(){
            this.$api.orcamento.inventario.lista().then((response) => {
                if(response.status == 200){
                    this.listaProdutos    =   response.data;
                }
            });  
        },        

      
    },

    /* */
    mounted(){


  // Set up our event listener to run the startup process
  // once loading is complete.
    this.startup();
    this.getProdutos();

    } ,  


    validations: {
        objInventario: {

            descricao:{required},
            marca:{required},
            qtd:{required},
        }




    },
            
        
}
</script>

