import request from '@/utils/request'

// 查询报销审核列表
export function listReimbursement(query) {
  return request({
    url: '/system/reimbursement/list',
    method: 'get',
    params: query
  })
}

// 查询报销审核详细
export function getReimbursement(id) {
  return request({
    url: '/system/reimbursement/' + id,
    method: 'get'
  })
}

// 新增报销审核
export function addReimbursement(data) {
  return request({
    url: '/system/reimbursement',
    method: 'post',
    data: data
  })
}

// 修改报销审核
export function updateReimbursement(data) {
  return request({
    url: '/system/reimbursement',
    method: 'put',
    data: data
  })
}

// 删除报销审核
export function delReimbursement(id) {
  return request({
    url: '/system/reimbursement/' + id,
    method: 'delete'
  })
}
