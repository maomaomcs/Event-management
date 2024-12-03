import request from '@/utils/request'

// 查询个人赛事11列表
export function listGRcompetitions1(query) {
  return request({
    url: '/system/GRcompetitions1/list',
    method: 'get',
    params: query
  })
}

// 查询个人赛事11详细
export function getGRcompetitions1(id) {
  return request({
    url: '/system/GRcompetitions1/' + id,
    method: 'get'
  })
}

// 新增个人赛事11
export function addGRcompetitions1(data) {
  return request({
    url: '/system/GRcompetitions1',
    method: 'post',
    data: data
  })
}

// 修改个人赛事11
export function updateGRcompetitions1(data) {
  return request({
    url: '/system/GRcompetitions1',
    method: 'put',
    data: data
  })
}

// 删除个人赛事11
export function delGRcompetitions1(id) {
  return request({
    url: '/system/GRcompetitions1/' + id,
    method: 'delete'
  })
}
