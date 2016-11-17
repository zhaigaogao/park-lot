/**
 * 人员管理
 * Qichen.Zheng <qichen.zheng@pactera.com>
 */
'use strict';
define(function (require, exports, module) {
    var $ = require('jquery'),
        _ = require('underscore'),
        Backbone = require('backbone'),
    template = require('template');
    var alert = require('component/Alert');
    var confirm = require('component/Confirm');
    require('jquery-util');

    var collectionControls = require('component/collection-controls'),
        collectionExtension = require('component/collection-extension'),
        TableView = collectionControls.TableView,
        TableRowView = collectionControls.TableRowView,
        PaginationView = collectionControls.PaginationView,
        BaseItemSetting = collectionExtension.BaseItemSetting;
    var TreeView = require('component/treeview');
    var Modal = require('component/Modal');
    var FormModal = require('component/FormModal');

    var urls = {
        'findDept': CONTEXT_PATH + '/user/findOrgByPreId.do',
        'allDept': CONTEXT_PATH + '/user/findAllOrgs.do',
        'addDept': CONTEXT_PATH + '/user/saveDept.do',
        'updateDept': CONTEXT_PATH + '/user/updateDept.do',
        'delDept': CONTEXT_PATH + '/user/delDept.do',

        'findUsers': CONTEXT_PATH + '/user/findUsers.do',
        'deleteUser': CONTEXT_PATH + '/user/deleteUser.do',
        'addUser': CONTEXT_PATH + '/user/saveUser.do',
        'updateUser': CONTEXT_PATH + '/user/updateUser.do',

        'addCar': CONTEXT_PATH + '/vehicInfo/addVehicInfo.do',
        'deleteCar': CONTEXT_PATH + '/vehicInfo/deleteVehicInfo.do',
        'queryCars': CONTEXT_PATH + '/vehicInfo/queryVehicVoByUserId.do'
    };

    var deptEditModalRender = template($('#tmpl-deptModal').html());
    var DeptEditModal = FormModal.extend({
        template: deptEditModalRender,
        validate: {
            errorClass: 'error-valid',
            rules: {
                'orgName': {
                    required: true,
                    maxlength: 20
                },
                'showindex': {
                    required: true,
                    digits: true,
                    maxlength: 5
                }
            },
            messages: {
                'orgName': {
                    required: '部门名称为必填项',
                    maxlength: '最多只能输入20个字符'
                },
                'showindex': {
                    required: '排序序号为必填项',
                    digits: '必须输入整数',
                    maxlength: '最多只能输入5个字符'
                }
            }
        },
        submit: function (event) {
            if (!this.$form.valid()) { return; }

            var params = this.$form.serializeObject();
            var $target = $(event.target);
            var _isNew = !params.id || params.id == '';

            $.ajax({
                url: _isNew ? urls.addDept : urls.updateDept,
                type: 'post',
                dataType: 'json',
                context: this,
                data: params,
                beforeSend: function () {
                    $target.prop('disabled', true);
                },
                complete: function () {
                    $target.prop('disabled', false);
                }
            }).done(function (res) {
                res = _.extend({
                    success: false,
                    message: '操作失败'
                }, res);

                if (res.success) {
                    alert('操作成功').delay(1);
                    if (_isNew) {
                        var model = _.pick(res.model, 'id', 'previousId', 'orgName', 'showindex');
                        model.name = model.orgName;
                        model.pId = model.previousId;
                        Backbone.trigger('save:dept', model);
                    } else {
                        this.model.set({
                            name: params.orgName,
                            orgName: params.orgName,
                            showindex: params.showindex
                        });
                    }
                    this.hide();
                } else {
                    alert(res.message);
                }
            }).fail(function () {
                alert('请求失败');
            });
        }
    });

    var DeptCollection = Backbone.Collection.extend({
        url: urls.allDept,
        parse: function (res) {
            res = _.extend({
                model: []
            }, res);

            var models = res.model;
            var parsed = [];
            if (_.isArray(models)) {
                _.each(models, function (model) {
                    var attrs = _.pick(model, 'id', 'previousId', 'showindex', 'orgName');
                    attrs.pId = model.previousId;
                    attrs.name = model.orgName;
                    parsed.push(attrs);
                });
            }

            return parsed;
        }
    });
    var DeptTreeView = TreeView.extend({
        initialize: function (options) {
            options || (options = {});

            this.listenTo(this.collection, 'add', this.addOne);
            this.listenTo(this.collection, 'remove', this.removeOne);
            this.listenTo(this.collection, 'destroy', this.removeOne);
            this.listenTo(this.collection, 'change', this.changeOne);
            this.listenTo(this.collection, 'sync', this.sync);
            this.listenTo(this.collection, 'reset', this.reset);

            this.listenTo(Backbone, 'save:dept', this.createOne);

            this.cacheEls();
            this.treeOptions = this.treeOptions();
            this.initTree();
        },
        changeOne: function (model, options) {
            var node = this.getNodeById(model.id);
            node.name = model.get('name');
            this.tree.updateNode(node);
        },
        getNodeById: function (id) {
            return this.tree.getNodeByParam('id', id);
        },
        onClick: function (event, treeId, treeNode, clickFlag) {
            var model = this.collection.get(treeNode.id);
            if (model) Backbone.trigger('select:dept', model);
        }
    });

    var empEditModalRender = template($('#tmpl-empModal').html());
    var EmpEditModal = FormModal.extend({
        template: empEditModalRender,
        validate: {
            errorClass: 'error-valid',
            rules: {
                'userName': {
                    required: true,
                    maxlength: 20
                },
                'mobile': {
                    required: true,
                    mobile: true
                },
                'showindex': {
                    required: true,
                    digits: true,
                    maxlength: 5
                }
            },
            messages: {
                'userName': {
                    required: '姓名为必填项',
                    maxlength: '最多只能输入20个字符'
                },
                'mobile': {
                    required: '手机为必填项'
                },
                'showindex': {
                    required: '排序序号为必填项',
                    digits: '必须输入整数',
                    maxlength: '最多只能输入5个字符'
                }
            }
        },
        submit: function (event) {
            if (!this.$form.valid()) { return; }

            var params = this.$form.serializeObject();
            var $target = $(event.target);
            var _isNew = !params.id || params.id == '';

            $.ajax({
                url: _isNew ? urls.addUser : urls.updateUser,
                type: 'post',
                dataType: 'json',
                context: this,
                data: params,
                beforeSend: function () {
                    $target.prop('disabled', true);
                },
                complete: function () {
                    $target.prop('disabled', false);
                }
            })
            .done(function (res) {
                res = _.extend({
                    success: false,
                    message: '操作失败'
                }, res);

                if (res.success) {
                    alert('操作成功').delay(1);
                    if (_isNew) {
                        Backbone.trigger('save:emp', params);
                    } else {
                        this.model.set({
                            name: params.orgName,
                            orgName: params.orgName,
                            showindex: params.showindex
                        });
                    }
                    this.hide();
                } else {
                    alert(res.message);
                }
            })
            .fail(function () {
                alert('请求失败');
            });
        }
    });

    var carsModalRender = template($('#tmpl-carModal').html());
    var CarModel = Backbone.Model.extend({
        pending: false,
        destroy: function () {
            if (this.pending) return false;

            var data = this.toJSON();
            $.ajax({
                context: this,
                url: urls.deleteCar,
                type: 'post',
                dataType: 'json',
                data: {
                    id: data.id
                },
                beforeSend: function () {
                    this.pending = true;
                },
                complete: function () {
                    this.pending = false;
                }
            }).done(function (resp) {
                _.defaults(resp, {
                    success: false,
                    message: '未知错误'
                });
                if (resp.success) {
                    this.stopListening();
                    this.trigger('destroy', this, this.collection);
                } else {
                    alert(resp.message).delay(3);
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + '：' + errorThrown).delay(3);
            });
        }
    });
    var CarRowView = TableRowView.extend({
        events: {
            'click [role="remove"]': 'doRemove'
        },
        doRemove: function () {
            var _this = this;
            confirm('确定删除?', function () {
                _this.model.destroy();
            });
        }
    });
    var CarsModal = FormModal.extend({
        template: carsModalRender,
        initialize: function (options) {
            this.constructor.__super__.initialize.apply(this, arguments);
        },
        render: function () {
            this.constructor.__super__.render.apply(this, arguments);
            this.initCarTable();
            return this;
        },
        initCarTable: function () {
            this.carTable = new TableView({
                el: this.$('table'),
                store: {
                    url: urls.queryCars,
                    model: CarModel,
                    pagination: {
                        enable: false
                    },
                    extractResult: function (res) { return res.model; },
                    checkSuccess: function (res) { return res.success; }
                },
                rowView: CarRowView,
                row: {
                    template: template($('#tmpl-car-row').html())
                }
            });
            this.carTable.store.query({ userId: this.model.get('id') });
        },
        submit: function (event) {
            var $form = this.$form;
            var carTable = this.carTable;
            var params = $form.serializeObject();
            var $target = $(event.target);

            $.ajax({
                url: urls.addCar,
                dataType: 'json',
                type: 'post',
                data: params,
                beforeSend: function () {
                    $target.prop('disabled', true);
                },
                complete: function () {
                    $target.prop('disabled', false);
                }
            })
            .done(function (resp) {
                if (resp.success) {
                    carTable.store.update();
                    $form[0].reset();
                }
                else {
                    alert(resp.message);
                }
            })
            .fail(function () {
                alert('请求失败！');
            });
        }
    });

    var EmpModel = Backbone.Model.extend({
        pending: false,
        destroy: function () {
            if (this.pending) return false;

            var data = this.toJSON();
            $.ajax({
                context: this,
                url: urls.deleteUser,
                type: 'post',
                dataType: 'json',
                data: {
                    userId: data.id
                },
                beforeSend: function () {
                    this.pending = true;
                },
                complete: function () {
                    this.pending = false;
                }
            }).done(function (resp) {
                _.defaults(resp, {
                    success: false,
                    message: '未知错误'
                });
                if (resp.success) {
                    this.stopListening();
                    this.trigger('destroy', this, this.collection);
                } else {
                    alert(resp.message).delay(3);
                }
            }).fail(function (jqXHR, textStatus, errorThrown) {
                alert(textStatus + '：' + errorThrown).delay(3);
            });
        }
    });
    var EmpRowView = TableRowView.extend({
        events: {
            'click [role="edit"]': 'doEdit',
            'click [role="remove"]': 'doRemove',
            'click [role="car"]': 'doSetCar'
        },
        doEdit: function () {
            var modal = new EmpEditModal({
                model: this.model
            });
            modal.render().$el.appendTo(document.body);
            modal.show();
        },
        doRemove: function () {
            var _this = this;
            confirm('确定删除?', function () {
                _this.model.destroy();
            });
        },
        doSetCar: function () {
            var modal = new CarsModal({
                model: this.model
            });
            modal.render().$el.appendTo(document.body);
            modal.show();
        }
    });

    var ButtonsView = Backbone.View.extend({
        events: {
            'click [role="remove"]': 'doRemove',
            'click [role="edit"]': 'doEdit',
            'click [role="add-child"]': 'doAddChild',
            'click [role="add-user"]': 'doAddUser'
        },
        bind: function (model) {
            this.model = model;
        },
        doEdit: function () {
            if (!this.model) {
                alert('请先选择部门!').delay(3);
                return;
            }

            var modal = new DeptEditModal({
                model: this.model
            });
            modal.render().$el.appendTo(document.body);
            modal.show();
        },
        doRemove: function () {
            if (!this.model) {
                alert('请先选择部门!').delay(3);
                return;
            }

            var model = this.model;
            var data = model.toJSON();
            confirm('确认删除?', function () {
                $.ajax({
                    url: urls.delDept,
                    dataType: 'json',
                    data: { orgId: data.id },
                }).done(function (resp) {
                    resp = _.extend({
                        success: false,
                        message: '操作失败'
                    }, resp);

                    if (resp.success) {
                        model.trigger('destroy', model);
                        alert('操作成功').delay(1);
                    } else {
                        alert(resp.message);
                    }
                }).fail(function () {
                    alert('请求失败');
                });
            });
        },
        doAddChild: function () {
            if (!this.model) {
                alert('请先选择部门!').delay(3);
                return;
            }

            var attrs = {
                id: '',
                previousId: this.model.get('id'),
                orgName: '',
                showindex: '100'
            };
            var modal = new DeptEditModal({
                model: new Backbone.Model(attrs)
            });
            modal.render().$el.appendTo(document.body);
            modal.show();
        },
        doAddUser: function () {
            if (!this.model) {
                alert('请先选择部门!').delay(3);
                return;
            }

            var attrs = {
                id: '',
                orgId: this.model.get('id'),
                userName: '',
                mobile: '',
                post: '',
                workNumber: '',
                showindex: '100'
            };
            var modal = new EmpEditModal({
                model: new Backbone.Model(attrs)
            });
            modal.render().$el.appendTo(document.body);
            modal.show();
        }
    });

    var run = function () {
        var tableView = new TableView({
            el: '#emp-table',
            store: {
                url: urls.findUsers,
                model: EmpModel,
                pagination: {
                    enable: true,
                    extractPage: function (res) { return res.model; }
                },
                extractResult: function (res) { return res.model.list; },
                checkSuccess: function (res) { return res.success; }
            },
            rowView: EmpRowView,
            row: {
                template: template($('#tmpl-row').html())
            },
            sync: true
        });
        var pageView = new PaginationView({
            el: '#pagination'
        });
        pageView.bind(tableView);
        Backbone.on('save:emp', function () {
            tableView.store.update();
        });

        var deptList = new DeptCollection();
        var deptTree = new DeptTreeView({
            el: '#emp-tree',
            collection: deptList
        });

        $('.btn[role="add-dept"]').on('click', function () {
            var attrs = {
                id: '',
                previousId: '',
                orgName: '',
                showindex: '100'
            };
            var modal = new DeptEditModal({
                model: new Backbone.Model(attrs)
            });
            modal.render().$el.appendTo(document.body);
            modal.show();
        });

        var buttonsView = new ButtonsView({
            el: '#buttons'
        });
        Backbone.on('select:dept', function (model) {
            tableView.store.query({ orgId: model.get('id') });
            buttonsView.bind(model);
        });

        deptList.on('reset', function () {
            var roots = deptTree.tree.getNodes();
            if (roots.length > 0) {
                $('#' + roots[0].tId + ' > a').click();
            }
        });
        deptList.fetch({
            type: 'post',
            reset: true,
            parse: true,
            data: {
                type: 0
            }
        });
    };

    module.exports = {
        run: run
    };
});
