import request from '@/utils/request'

// 查询个人赛事列表
export function listRegistration(query) {
  return request({
    url: '/system/registration/list',
    method: 'get',
    params: query
  })
}

// 查询个人赛事详细
export function getRegistration(id) {
  return request({
    url: '/system/registration/' + id,
    method: 'get'
  })
}

// 新增个人赛事
export function addRegistration(data) {
  return request({
    url: '/system/registration',
    method: 'post',
    data: data
  })
}

// 修改个人赛事
export function updateRegistration(data) {
  return request({
    url: '/system/registration',
    method: 'put',
    data: data
  })
}

// 删除个人赛事
export function delRegistration(id) {
  return request({
    url: '/system/registration/' + id,
    method: 'delete'
  })
}
