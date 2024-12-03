import request from '@/utils/request'

// 查询个人赛事副本列表
export function listGRcompetitions3(query) {
  return request({
    url: '/system/GRcompetitions3/list',
    method: 'get',
    params: query
  })
}

// 查询个人赛事副本详细
export function getGRcompetitions3(id) {
  return request({
    url: '/system/GRcompetitions3/' + id,
    method: 'get'
  })
}

// 新增个人赛事副本
export function addGRcompetitions3(data) {
  return request({
    url: '/system/GRcompetitions3',
    method: 'post',
    data: data
  })
}

// 修改个人赛事副本
export function updateGRcompetitions3(data) {
  return request({
    url: '/system/GRcompetitions3',
    method: 'put',
    data: data
  })
}

// 删除个人赛事副本
export function delGRcompetitions3(id) {
  return request({
    url: '/system/GRcompetitions3/' + id,
    method: 'delete'
  })
}
