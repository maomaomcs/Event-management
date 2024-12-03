import request from '@/utils/request'

// 查询赛事登记副本列表
export function listCompetitions3(query) {
  return request({
    url: '/system/competitions3/list',
    method: 'get',
    params: query
  })
}

// 查询赛事登记副本详细
export function getCompetitions3(id) {
  return request({
    url: '/system/competitions3/' + id,
    method: 'get'
  })
}

// 新增赛事登记副本
export function addCompetitions3(data) {
  return request({
    url: '/system/competitions3',
    method: 'post',
    data: data
  })
}

// 修改赛事登记副本
export function updateCompetitions3(data) {
  return request({
    url: '/system/competitions3',
    method: 'put',
    data: data
  })
}

// 删除赛事登记副本
export function delCompetitions3(id) {
  return request({
    url: '/system/competitions3/' + id,
    method: 'delete'
  })
}
