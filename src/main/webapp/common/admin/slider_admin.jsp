<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- SIDEBAR -->
<section id="sidebar">
    <a href="/admin/home" class="brand">
        <i class='bx bxs-smile'></i>
        <span class="text">AdminPage</span>
    </a>
    <ul class="side-menu top">
        <li class="${pageName == '/admin/home' ? 'active' : ''}">
            <a href="/admin/home">
               <i class='bx bxs-dashboard'></i>
                <span class="text">Dash board</span>
            </a>
        </li>
        
        
        <li>
            <a href="/admin/products">
                <i class='bx bxs-store-alt' ></i>
                <span class="text">Product Management</span>
            </a>
        </li>
        
         <li >
            <a href="#" class="sub-btn">
	            <i class='bx bxs-file-blank' ></i>
	             <span class="text">Drafts</span>
	          	<i style="margin-left: 50%;" class='bx bxs-chevron-right dropdown'></i>
            </a>
            
            
          <ul class="sub-menu">
          	<li style="margin-left:48px"><a href="/admin/draft/products">Product</a></li>
          	<li style="margin-left:48px"><a href="/admin/draft/news">News</a></li>
          </ul>
            
            
          
            
           
        </li>
        
        
         <li>
            <a href="/admin/categories">
                <i class='bx bxs-category' ></i>
                <span class="text">Categories</span>
            </a>
        </li>
        
        
       
        
      
        <li>
            <a href="/admin/orders">
                <i class='bx bxs-calculator'></i>
                <span class="text">Orders</span>
            </a>
        </li>
      
        <li>
            <a href="/admin/users">
                <i class='bx bxs-user'></i>
                <span class="text">Customers</span>
            </a>
        </li>
        
         <li>
            <a href="/admin/staffs">
               <i class='bx bxs-user-plus' ></i>
                <span class="text">Staffs</span>
            </a>
        </li>
    
      
       <li>
            <a href="/admin/contact">
            	<i class='bx bx-comment'></i>
                <span class="text">Comment Management</span>
            </a>
        </li>
        <li>
            <a href="/admin/chat">
             <i class='bx bxs-comment-dots'></i>
                <span class="text">Chat</span>
            </a>
        </li>
        
        <li>
            <a href="/admin/news">
           <i class='bx bxs-news' ></i>
                <span class="text">News</span>
            </a>
        </li>
        
        <li>
            <a href="/admin/chart">
               <i class='bx bxs-bar-chart-alt-2'></i>
                <span class="text">Sales Analytics</span>
            </a>
        </li>
    </ul>
    
    
    
    <ul class="side-menu">

        <li>

            
            <a href="/admin/logout"  class="logout">
                <i class='bx bxs-log-out-circle'></i>
                <span class="text">Logout</span>
            </a>
          

        </li>
    </ul>
</section>
<!-- SIDEBAR -->