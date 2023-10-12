<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <!-- NAVBAR -->
            <nav>
                <i class='bx bx-menu'></i>
                <a href="admin-home" class="nav-link">HOME</a>
                <form action="admin-search">
                    <div class="form-input">
                        <input id="searchMode" type="hidden" value="" name="searchMode">
                        <input name="searchQ" type="search" placeholder="Search...">
                        <button type="submit" class="search-btn"><i class='bx bx-search'></i></button>
                    </div>
                </form>
                <input type="checkbox" id="switch-mode" hidden>

               
            </nav>
            <!-- NAVBAR -->