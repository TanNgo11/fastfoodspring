<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<header>
    <div class="nav-header">
        <div class="container">
            <div class="row ">
                <div class="col-md-5 text-center">
                    <nav class="top-nav">
                        <ul>
                            <li class=""><a title="Home" href="/home">Home</a></li>
                            <li class=""><a href="tel:+0929234798" title="Booking">Contact</a></li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button"
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    Categories
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="Category?cid=2">Drink</a>
                                    <a class="dropdown-item" href="Category?cid=1" >Food</a>
                                </div>
                            </li>
                        </ul>
                    </nav>
                </div>
                <div class="col-md-2 text-center">
                    <h1 class="logo">
                        <a title="CrispTrek" href="/home">
                            <img src="${pageContext.servletContext.contextPath}/inc/FoodTakeAwayRestaurantLogo.png" alt="CrispTrek">
                        </a>
                    </h1>
                </div>
                <div class="col-md-5">

                    <div class="row h-100">

                        <div class="col-md-8 align-self-lg-center">
                            <div class="input-group">
                                <div class="searchInput">
									<input type="text" placeholder="Search your product">
									<div class="resultBox">
										<!-- here list are inserted from javascript -->
									</div>
									
									<div class="icon">
									<a id="searchLink" href="/search?q="><i class="fa fa-search" aria-hidden="true"></i></a>
										
									</div>
								</div>




                            </div>
                        </div>

                        <div class="col-md-4 align-self-lg-center">
                            <div href="" class="user">
                               <security:authorize access="isAuthenticated()">
									<div id="username" class="userName"><%=SecurityUtils.getPrincipal().getFullName()%><i class="fa fa-caret-down" aria-hidden="true"></i></div>
									
								</security:authorize>
								<security:authorize access="isAnonymous()">
										<i class="fa fa-2x fa-user"></i>
									</security:authorize>
                                <div class="userMenu">
                                    <c:if test="${sessionScope.logedUser!=null}">
                                        <ul>
                                            <li><a style="text-decoration: none; color: black" href="cart-view">View Cart</a></li>
                                            <li><a style="text-decoration: none; color: black" href="user-view?mode=view">Edit profile</a></li>
                                            <li><a style="text-decoration: none; color: black" href="view-bill">View Bill</a></li>
                                            <li>
                                                <form method="POST" action="login">
                                                    <input type="hidden" name="mode" value="logout">
                                                    <button class="logout-btn" type="submit">Logout</button>
                                                </form>
                                            </li>
                                        </ul>
                                    </c:if>
                                    <c:if test="${sessionScope.logedUser==null}">
                                        <ul>
                                            <li><a href="/login">Login</a></li>
                                        </ul>
                                    </c:if>


                                </div>
                            </div>
                            <a href="/cart" class="cart"> <span class="cc_cart_count">
									<c:if test="${sessionScope.cart !=null }">
										<span id="count_in_cart" class="cc_cart_count_child">${sessionScope.cart.items.size()}</span>
										<i class="fa fa-2x fa-shopping-cart"></i>
							</span> </c:if> <c:if test="${sessionScope.cart ==null }">
									<span id="count_in_cart" class="cc_cart_count_child">0</span>
									<i class="fa fa-2x fa-shopping-cart"></i>
									</span>
								</c:if>
							</a>
                        </div>

                    </div>

                </div>

            </div>
        </div>
    </div>
    <div class="nav-mobile">
        <div class="container">
            <div class="row">
                <div class="col-4">
                    <div class="menu-mobile-icon">
                        <i class="fa fa-bars fa-3x" aria-hidden="true"></i>
                    </div>
                </div>
                <div class="col-4">
                    <div class="logo-mobile">
                        <a title="CrispTrek" href="home">
                            <img src="${pageContext.servletContext.contextPath}/inc/FoodTakeAwayRestaurantLogo.png" alt="CrispTrek">
                        </a>
                    </div>
                </div>
                <div style="text-align: end" class="col-4">




                    <a href="cart-view" class="cart">
                        <i class="fa fa-3x fa-shopping-cart"></i>
                    </a>
                </div>
            </div>
        </div>
    </div>


</header>