import request from '@/utils/request'

// 查询赛事报销列表
export function listParticipants(query) {
  return request({
    url: '/system/participants/list',
    method: 'get',
    params: query
  })
}

// 查询赛事报销详细
export function getParticipants(id) {
  return request({
    url: '/system/participants/' + id,
    method: 'get'
  })
}

// 新增赛事报销
export function addParticipants(data) {
  return request({
    url: '/system/participants',
    method: 'post',
    data: data
  })
}

// 修改赛事报销
export function updateParticipants(data) {
  return request({
    url: '/system/participants',
    method: 'put',
    data: data
  })
}

// 删除赛事报销
export function delParticipants(id) {
  return request({
    url: '/system/participants/' + id,
    method: 'delete'
  })
}
