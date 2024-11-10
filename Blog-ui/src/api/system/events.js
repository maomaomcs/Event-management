import request from '@/utils/request'

// 查询赛事记录列表
export function listEvents(query) {
  return request({
    url: '/system/events/list',
    method: 'get',
    params: query
  })
}

// 查询赛事记录详细
export function getEvents(id) {
  return request({
    url: '/system/events/' + id,
    method: 'get'
  })
}

// 新增赛事记录
export function addEvents(data) {
  return request({
    url: '/system/events',
    method: 'post',
    data: data
  })
}

// 修改赛事记录
export function updateEvents(data) {
  return request({
    url: '/system/events',
    method: 'put',
    data: data
  })
}

// 删除赛事记录
export function delEvents(id) {
  return request({
    url: '/system/events/' + id,
    method: 'delete'
  })
}
