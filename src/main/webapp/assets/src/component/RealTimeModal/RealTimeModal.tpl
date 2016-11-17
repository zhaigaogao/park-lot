<div class="modal-dialog">
    <div class="modal-content">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <h4 class="modal-title">实时进出信息</h4>
        </div>
        <div class="modal-body">
            <table class="table table-bordered realtime-table">
                <caption>{{model.carNumber}}</caption>
                <tbody>
                    <# if (model.carType == '1') { #>
                        <tr>
                            <th>车主</th>
                            <td>{{model.carUserName}}</td>
                            <th>车主所在部门</th>
                            <td>{{model.carUserDept}}</td>
                        </tr>
                        <tr>
                            <th>车主电话</th>
                            <td>{{model.carUserPhone}}</td>
                            <td colspan="2"></td>
                        </tr>
                    <# } else if (model.carType == '2') { #>
                        <tr>
                            <th>联系人</th>
                            <td>{{model.visitUserName}}</td>
                            <th>联系人公司</th>
                            <td>{{model.visitUserComp}}</td>
                        </tr>
                        <tr>
                            <th>联系人电话</th>
                            <td>{{model.visitUserPhone}}</td>
                            <td colspan="2"></td>
                        </tr>
                        <tr>
                            <th>预约人</th>
                            <td>{{model.regUserName}}</td>
                            <th>预约人部门</th>
                            <td>{{model.regUserDept}}</td>
                        </tr>
                        <tr>
                            <th>预约人电话</th>
                            <td>{{model.regUserPhone}}</td>
                            <td colspan="2"></td>
                        </tr>
                        <tr>
                            <th>预约开始时间</th>
                            <td>{{model.regInTime}}</td>
                            <th>预约结束时间</th>
                            <td>{{model.regOutTime}}</td>
                        </tr>
                    <# } #>
                    <tr>
                        <td class="msg" colspan="4">{{model.retMsg}}</td>
                    </tr>
                </tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
        </div>
    </div>
</div>
