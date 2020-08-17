<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ tag language="java" pageEncoding="UTF-8"%>
 <form style="border: 0px">
 <div class="language-box" >
                <select id="language" name="language" onchange="submit()">
                    <option value="en" ${pageContext.response.locale == 'en' ? 'selected' : ''}>English</option>
                    <option value="ru" ${pageContext.response.locale == 'ru' ? 'selected' : ''}>Русский</option>
                    <option value="ua" ${pageContext.response.locale == 'ua' ? 'selected' : ''}>Українська</option>

       </select>
  </form>
 </div>