import request from '@/utils/request'

// 查询个人赛事副本列表
export function listGRcompetitions2(query) {
  return request({
    url: '/system/GRcompetitions2/list',
    method: 'get',
    params: query
  })
}

// 查询个人赛事副本详细
export function getGRcompetitions2(id) {
  return request({
    url: '/system/GRcompetitions2/' + id,
    method: 'get'
  })
}

// 新增个人赛事副本
export function addGRcompetitions2(data) {
  return request({
    url: '/system/GRcompetitions2',
    method: 'post',
    data: data
  })
}

// 修改个人赛事副本
export function updateGRcompetitions2(data) {
  return request({
    url: '/system/GRcompetitions2',
    method: 'put',
    data: data
  })
}

// 删除个人赛事副本
export function delGRcompetitions2(id) {
  return request({
    url: '/system/GRcompetitions2/' + id,
    method: 'delete'
  })
}
