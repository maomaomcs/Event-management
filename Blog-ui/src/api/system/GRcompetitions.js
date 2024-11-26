import request from '@/utils/request'

// 查询赛事报名列表
export function listGRcompetitions(query) {
  return request({
    url: '/system/GRcompetitions/list',
    method: 'get',
    params: query
  })
}

// 查询赛事报名详细
export function getGRcompetitions(id) {
  return request({
    url: '/system/GRcompetitions/' + id,
    method: 'get'
  })
}

// 新增赛事报名
export function addGRcompetitions(data) {
  return request({
    url: '/system/GRcompetitions',
    method: 'post',
    data: data
  })
}

// 修改赛事报名
export function updateGRcompetitions(data) {
  return request({
    url: '/system/GRcompetitions',
    method: 'put',
    data: data
  })
}

// 删除赛事报名
export function delGRcompetitions(id) {
  return request({
    url: '/system/GRcompetitions/' + id,
    method: 'delete'
  })
}
