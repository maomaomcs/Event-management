import request from '@/utils/request'

// 查询团队赛事登记列表
export function listTDcompetitions(query) {
  return request({
    url: '/system/TDcompetitions/list',
    method: 'get',
    params: query
  })
}

// 查询团队赛事登记详细
export function getTDcompetitions(id) {
  return request({
    url: '/system/TDcompetitions/' + id,
    method: 'get'
  })
}

// 新增团队赛事登记
export function addTDcompetitions(data) {
  return request({
    url: '/system/TDcompetitions',
    method: 'post',
    data: data
  })
}

// 修改团队赛事登记
export function updateTDcompetitions(data) {
  return request({
    url: '/system/TDcompetitions',
    method: 'put',
    data: data
  })
}

// 删除团队赛事登记
export function delTDcompetitions(id) {
  return request({
    url: '/system/TDcompetitions/' + id,
    method: 'delete'
  })
}
