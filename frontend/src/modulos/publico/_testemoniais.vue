<template>
		<section class="testimonial-section">
			<div class="auto-container">
				<!-- Sec Title -->
				<div class="sec-title-two centered">
					<h2>O que nossos clientes dizem</h2>
					<div class="title-text">Thousands of people done interior</div>
				</div>
				
				<div class="testimonial-carousel owl-carousel owl-theme">
					
					<!-- Testimonial Block -->
					<div class="testimonial-block">
						<div class="inner-box">
							<div class="content">
								<div class="image-outer">
									<div class="image">
										<img src="images/resource/author-1.jpg" alt="" />
									</div>
								</div>
								<h3>Michale John</h3>
								<div class="title">I got luxuary inteior from Stella Orr'e</div>
								<div class="text">Osed quia consequuntur magni dolores eos qui rati one voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci sed quia non numqua.</div>
							</div>
						</div>
					</div>
					
					<!-- Testimonial Block -->
					<div class="testimonial-block">
						<div class="inner-box">
							<div class="content">
								<div class="image-outer">
									<div class="image">
										<img src="images/resource/author-2.jpg" alt="" />
									</div>
								</div>
								<h3>Michale John</h3>
								<div class="title">I got luxuary inteior from Stella Orr'e</div>
								<div class="text">Osed quia consequuntur magni dolores eos qui rati one voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci sed quia non numqua.</div>
							</div>
						</div>
					</div>
					
					<!-- Testimonial Block -->
					<div class="testimonial-block">
						<div class="inner-box">
							<div class="content">
								<div class="image-outer">
									<div class="image">
										<img src="images/resource/author-1.jpg" alt="" />
									</div>
								</div>
								<h3>Michale John</h3>
								<div class="title">I got luxuary inteior from Stella Orr'e</div>
								<div class="text">Osed quia consequuntur magni dolores eos qui rati one voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci sed quia non numqua.</div>
							</div>
						</div>
					</div>
					
					<!-- Testimonial Block -->
					<div class="testimonial-block">
						<div class="inner-box">
							<div class="content">
								<div class="image-outer">
									<div class="image">
										<img src="images/resource/author-2.jpg" alt="" />
									</div>
								</div>
								<h3>Michale John</h3>
								<div class="title">I got luxuary inteior from Stella Orr'e</div>
								<div class="text">Osed quia consequuntur magni dolores eos qui rati one voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci sed quia non numqua.</div>
							</div>
						</div>
					</div>
					
				</div>
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
      
// Testimonial Carousel
	if ($('.testimonial-carousel').length) {
		$('.testimonial-carousel').owlCarousel({
			animateOut: 'slideOutDown',
animateIn: 'fadeIn',
			loop:true,
			margin:30,
			nav:true,
			smartSpeed: 500,
			autoHeight: false,
			autoplay: true,
			autoplayTimeout:10000,
			navText: [ '<span class="fa fa-angle-left"></span>', '<span class="fa fa-angle-right"></span>' ],
			responsive:{
				0:{
					items:1
				},
				600:{
					items:1
				},
				800:{
					items:2
				},
				1024:{
					items:2
				},
				1200:{
					items:2
				}
			}
		});    		
	}


	}        
        
}
</script>