<template>
<section class="fluid-section-one">
			<div class="outer-container clearfix">
				
				<!--Content Column-->
				<div class="content-column">
					<div class="content-box">
						<h2>Home design made easy</h2>
						<div class="text">To give you a home that lasts, we bring you only the best in everything — quality raw materials, state-of-the-art manufacturing, rigorous quality checks, professional installations and transparent prices.</div>
						<ul class="list-style-one">
							<li>Whole Home Interior</li>
							<li>Modular Kitchen and Wardrobe</li>
							<li>Furniture, Decore and more</li>
							<li>Post-surgery, including cosmetic, joint replacement, or heart surgery</li>
							<li>Chronic conditions, such as diabetes, COPD, or cancer</li>
							<li>On Site Expertiset</li>
						</ul>
						<div class="bold-text">Design your home, right here at Stella Orr'e <br> <a href="contact.html">Talk to a designer</a></div>
					</div>
				</div>
				
				<!--Image Column-->
				<div class="image-column" style="background-image: url(images/resource/video-image-3.jpg)">
					<div class="inner-column">
						<div class="image">
							<img src="images/resource/video-img.jpg" alt="">
						</div>
						<a href="https://www.youtube.com/watch?v=SXZXtD60t2g" class="overlay-link lightbox-image">
							<div class="icon-box">
								<span class="icon flaticon-play-button"></span>
								<i class="ripple"></i>
							</div>
						</a>
					</div>
				</div>
				<!--End Image Column-->
				
			</div>
		</section>

</template>
   

<script>
/* global $ */
export default {

    /* */
    components: {
    
    }, 

    /* */
    watch: {

    },

    /* */
    props: {
    },            


    /* */
    data() {
            return {
				
            }
        },

    /* */
	computed:{
	},

    /* */
    methods: {


    },

    /* */
    created() {
     
    },    

    /* */
    mounted(){
		//LightBox / Fancybox
		if($('.lightbox-image').length) {
			$('.lightbox-image').fancybox({
				openEffect  : 'fade',
				closeEffect : 'fade',
				helpers : {
					media : {}
				}
			});
		}      
	}        
        
}
</script>