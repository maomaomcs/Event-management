import request from '@/utils/request'

// 查询个人团队赛事列表
export function listGRtdcompetitions(query) {
  return request({
    url: '/system/GRtdcompetitions/list',
    method: 'get',
    params: query
  })
}

// 查询个人团队赛事详细
export function getGRtdcompetitions(id) {
  return request({
    url: '/system/GRtdcompetitions/' + id,
    method: 'get'
  })
}

// 新增个人团队赛事
export function addGRtdcompetitions(data) {
  return request({
    url: '/system/GRtdcompetitions',
    method: 'post',
    data: data
  })
}

// 修改个人团队赛事
export function updateGRtdcompetitions(data) {
  return request({
    url: '/system/GRtdcompetitions',
    method: 'put',
    data: data
  })
}

// 删除个人团队赛事
export function delGRtdcompetitions(id) {
  return request({
    url: '/system/GRtdcompetitions/' + id,
    method: 'delete'
  })
}
