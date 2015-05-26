<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.aggrepoint.com/winlet" prefix="win"%>

<div class="btn-group bottom_space" role="group">
	<button type="button" class="btn btn-default"
		onclick="win$.post('editRole', {rid: 0})">
		<span class="glyphicon glyphicon-plus"></span> 添加角色
	</button>
	<button type="button" class="btn btn-default" onclick="win$.post('assignRights', 'roleEdit')">
		<span class="glyphicon glyphicon-check"></span> 批量授权
	</button>
</div>

<win:form name="roleEdit" method="post">
<table class="table table-striped">
	<thead>
		<tr>
			<th><input type="checkbox" onclick="$(this).parents('form').find('input:checkbox').prop('checked', this.checked)" /></th>
			<th>角色</th>
			<th>权限 <span class="text-danger">(可拖拽)</span></th>
		</tr>
	</thead>
	<c:forEach var="role" items="${ROLES}">
		<tr>
			<td><input name="roleid" type="checkbox" value="${role.roleId}"  ${win:if(win:contains(CHECKED, role.roleId), "checked=checked")}/></td>
			<td>
				<div class="btn-group pull-right" role="group">
			      <button type="button" class="btn btn-xs btn-info" onclick="win$.post('editRole', {rid: ${role.roleId}})">
			        <span class="glyphicon glyphicon-pencil"></span>
				  </button>
			      <button type="button" class="btn btn-xs btn-warning" onclick="win$.post('deleteRole', {rid: ${role.roleId}})">
			        <span class="glyphicon glyphicon-trash"></span>
			      </button>
			    </div>
				<div>${role.name}</div>
				<div>${role.description}</div>
			</td>
			<td>
			  	<div class="winletdrag_area well" data-drag-group="rolerights" data-role-id="${role.roleId}">
					<c:forEach var="cat" items="${role.rightCats}">
						<p>
							${cat.name}
							<c:forEach var="right" items="${cat.rights}">
								<div class="winletdrag_cont pull-left" data-drag-group="rolerights" data-role-id="${role.roleId}" data-right-id="${right.rightId}">
									<span class="label label-default winletdrag_drag">${right.name}</span>&nbsp;
								</div>
							</c:forEach>
							<div class="clearfix"></div>
						</p>
					</c:forEach>
			  	</div>
			</td>
		</tr>
	</c:forEach>
</table>
</win:form>

<script>
// 拖动完毕后的处理
WinletDrag.config("rolerights", {
	showDropLocation: false,
	moveAfterDrag: false,
	dragEnd: function(cont, location) {
		if ($(cont).attr("data-role-id") && location == null) // 将已经分配的权限拖出了权限区域，删除
			win$.post('removeRight', {roleid: $(cont).attr("data-role-id"), rightid: $(cont).attr("data-right-id")});
	},
	dragged: function(cont, location) {
		if ($(cont).attr("data-role-id")) { // 拖动了已经分配的权限
			if ($(cont).attr("data-role-id") != $(location.area).attr("data-role-id")) // 拖到了其他角色的权限区域
				win$.post('moveRight', {from: $(cont).attr("data-role-id"), to: $(location.area).attr("data-role-id"), rightid: $(cont).attr("data-right-id")});
		} else { // 将未分配的权限拖动到角色权限区域，添加
			win$.post('addRight', {roleid: $(location.area).attr("data-role-id"), rightid: $(cont).attr("data-right-id")});
		}			
	}
});
</script>