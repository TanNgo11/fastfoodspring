<%-- 
    Document   : footer
    Created on : Jul 18, 2023, 8:59:06 PM
    Author     : TAN
--%>
<%@ page import="com.fastfood.utils.SecurityUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<footer style="margin-top: 100px;">
	<div class="info-footer">
		<div class="container">
			<div class="row">
				<div class="col-md-4 col-md-pull-4 col-sm-4 col-sm-pull-4">
					<div class="footer-widget">
						<h3 class="mb-4">CRISPTREK - FRESH AND NEW CHICKEN EVERYDAY</h3>
						<ul class="cms-lists-inner ">

							<p>Are you craving delicious, mouth-watering chicken? Look no
								further! Welcome to Scrisp Trek, your ultimate destination for
								the finest chicken offerings. Get ready for a tantalizing
								journey through flavors that will leave you begging for more.</p>

						</ul>
					</div>
				</div>
				<div class="col-md-4 col-md-push-4 col-sm-4 col-sm-push-4">
					<div class="footer-logo-and-info text-center">
						<a href="/" class="logo-wrapper " title="Evo Cake"
							style="display: block;"> <img
							style="width: 150px; display: block; margin: 0 auto;"
							src="${pageContext.servletContext.contextPath}/inc/FoodTakeAwayRestaurantLogo.png"
							alt="SrispTrek" class="lazy img-responsive center-block loaded">
						</a>
						<ul>
							<li><span class=""> Address: </span> <span class="">
									Thủ Dầu Một, Bình Dương </span></li>
							<li><span class=""> Hotline: </span> <a class=""
								href="tel:0908457153"> 0908457153 </a></li>
							<li><span class=""> Email: </span> <a class=""
								href="mailto:xxxxxxxxxx@gmail.com"> hoc.dai@eiu.edu.vn </a></li>
						</ul>
					</div>
				</div>

				<div class="col-md-4 col-sm-4">
					<div class="iframe-map">
						<iframe
							src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d31325.804539225563!2d106.65318249912107!3d11.059190093753799!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3174d1d7df763eaf%3A0xf4323e44f2867057!2sEastern%20International%20University!5e0!3m2!1sen!2s!4v1688627613028!5m2!1sen!2s"
							style="border: 0;" allowfullscreen="" loading="lazy"
							referrerpolicy="no-referrer-when-downgrade"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>


	<div class="copyright">
		<strong>&copy; 2023 CrispTrek All Rights Reserved</strong>
	</div>
</footer>
<div class="overlay-mobile-menu">
	<ul class="menu-mobile">

		<security:authorize access="isAnonymous()">


			<li><a href="login" class="user"> <i
					style="color: blue; margin-bottom: 30px;"
					class="fa fa-user-circle fa-2x icon-mobile-menu" aria-hidden="true"></i>
					<span>Login</span>
			</a></li>

		</security:authorize>
		<security:authorize access="isAuthenticated()">


			<li><a style="font-weight: bold"> <i
					style="color: blue; margin-bottom: 30px;"
					class="fa fa-user-circle fa-2x icon-mobile-menu" aria-hidden="true"></i><span><%=SecurityUtils.getPrincipal().getFullName()%></span></a>
			</li>

		</security:authorize>

		<li><a href="#introduction"><i
				class="fa fa-2x fa-home icon-mobile-menu" aria-hidden="true"></i><span>Home</span></a></li>
		<security:authorize access="isAuthenticated()">
			<li><a href="user-view?mode=view"><i
					class="fa fa fa-address-book fa-2x icon-mobile-menu"
					aria-hidden="true"></i><span>Change Profile</span></a></li>
			<li><a href="view-bill"><i
					class="fa fa fa-history fa-2x icon-mobile-menu" aria-hidden="true"></i><span>View
						Bills</span></a></li>

		</security:authorize>



		<li><a href=""><i
				class="fa fa fa-shopping-cart fa-2x icon-mobile-menu"
				aria-hidden="true"></i><span>View Cart</span></a></li>


		<li><a href="#product"><i
				class="fa fa-2x fa-list icon-mobile-menu" aria-hidden="true"></i><span>Category</span><i
				class="fa fa fa-angle-down  angle-right-icon" aria-hidden="true"></i></a>
			<ul class="sub-menu-mobile">
				<li style="padding-left: 73px;"><a href="">Food </a></li>
				<li style="padding-left: 73px;"><a href="">Drink</a></li>

			</ul></li>
		<li><a href="/contact" title="Booking"><i
				class="fa fa-2x fa-phone-square icon-mobile-menu" aria-hidden="true"></i><span>Contact
					us</span></a></li>
		<security:authorize access="isAuthenticated()">
			<form style="padding: 10px 8px;" method="POST" action="login">
				<input type="hidden" name="mode" value="logout">
				<button class="logout-btn" type="submit">
					<i class="fa fa-2x fa-sign-out icon-mobile-menu" aria-hidden="true"></i><span
						style="line-height: 6px; height: 15px; display: inline-block; vertical-align: middle;">Log
						out </span>
				</button>
			</form>



			<!--            <li><a href="">Sign out</a></li> -->
		</security:authorize>


	</ul>

	<div class="close-btn">&#10005;</div>
</div>
