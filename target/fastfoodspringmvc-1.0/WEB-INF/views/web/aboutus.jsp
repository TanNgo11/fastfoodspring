<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<section id="about" class="about mt-5">
		<div class="container aos-init aos-animate" data-aos="fade-up">
			<nav class="mt-3" aria-label="breadcrumb">
				<ol style="background-color: white; padding-left: 0px;"
					class="breadcrumb">
					<li class="breadcrumb-item"><a style="color: #007bff;"
						href="/home">Home</a></li>
					<li class="breadcrumb-item"><a href="#">About us</a></li>

				</ol>
			</nav>
			<h1 class="entry-title">
				<span>About Us</span>
			</h1>

			<div class="container">
				<div class="section-header">
					<h2 class="text-center">About Us</h2>
					<p class="text-center">
						Learn More <span>About Us</span>
					</p>
				</div>

				<div class="row gy-4">
					<div
						class="col-lg-7 position-relative about-img aos-init aos-animate"
						style="background-image: url(<c:url value='/inc/Other/about.jpg'/>) ;"
						data-aos="fade-up" data-aos-delay="150">
						<div class="call-us position-absolute">
							<h4>Book a Table</h4>
							<p>+1 5589 55488 55</p>
						</div>
					</div>
					<div class="col-lg-5 d-flex align-items-end aos-init aos-animate"
						data-aos="fade-up" data-aos-delay="300">
						<div class="content ps-0 ps-lg-5">
							<p class="fst-italic">Welcome to CrispTrek Fast Food – where
								flavor meets convenience in every delicious bite! At CrispTrek,
								we believe that fast food should not only be quick and
								convenient but also a celebration of bold flavors and
								high-quality ingredients. Our passion for delivering
								mouthwatering meals inspired us to create a culinary experience
								that satisfies your cravings and leaves you coming back for
								more.</p>

							<p>Welcome to CrispTrek Fast Food – where flavor meets
								convenience in every delicious bite! At CrispTrek, we believe
								that fast food should not only be quick and convenient but also
								a celebration of bold flavors and high-quality ingredients. Our
								passion for delivering mouthwatering meals inspired us to create
								a culinary experience that satisfies your cravings and leaves
								you coming back for more. Founded on the principles of quality,
								innovation, and customer satisfaction, CrispTrek Fast Food is
								your go-to destination for a delectable journey through an array
								of enticing menu options. Whether you're a seasoned foodie or
								someone looking for a quick and tasty meal on the go, our
								diverse menu caters to all taste buds and preferences.</p>

							<div class="position-relative mt-4">
								<img src="<c:url value='/inc/Other/about-2.jpg'/>"
									class="img-fluid" alt=""> <a
									href="https://www.youtube.com/watch?v=LXb3EKWsInQ"
									class="glightbox play-btn"></a>
							</div>
						</div>
					</div>
				</div>

			</div>
	</section>
	<div class="container mt-5 mb-5">
		<section class="mt-5">
			<div class="row">
				<div class="col-lg-4 text-center">
					<img class="rounded-circle mx-auto"
						src="<c:url value='/inc/Other/chef1.png'/>"
						alt="Generic placeholder image" width="140" height="140">
					<h2 class="mt-3">Culinary Maestro</h2>
					<p>Are you ready to embark on a culinary journey that
						tantalizes the taste buds and elevates the dining experience to
						new heights? We are seeking an exceptional Chef to join our team
						and bring a symphony of flavors to our patrons' palates</p>

				</div>
				<!-- /.col-lg-4 -->
				<div class="col-lg-4 text-center">
					<img class="rounded-circle mx-auto"
						src="<c:url value='/inc/Other/chef2.png'/>"
						alt="Generic placeholder image" width="140" height="140">
					<h2 class="mt-3">Gastronomic Visionary</h2>
					<p>Are you prepared to immerse yourself in a culinary odyssey
						that stimulates the senses and transforms ordinary dining into an
						extraordinary affair? We are on the lookout for an exceptional
						Chef to join our culinary ensemble and weave a tapestry of flavors
						that dance on the taste buds.</p>

				</div>
				<!-- /.col-lg-4 -->
				<div class="col-lg-4 text-center">
					<img class="rounded-circle mx-auto"
						src="<c:url value='/inc/Other/chef3.png'/>"
						alt="Generic placeholder image" width="140" height="140">
					<h2 class="mt-3">Epicurean Artisan</h2>
					<p>Are you ready to curate a symphony of flavors that
						captivates discerning palates and transforms dining into a sensory
						masterpiece? We are in search of an exceptional Chef to join our
						culinary brigade and be the maestro behind a culinary experience
						that transcends the ordinary.</p>

				</div>
				<!-- /.col-lg-4 -->
			</div>
		</section>


	</div>





</body>
</html>