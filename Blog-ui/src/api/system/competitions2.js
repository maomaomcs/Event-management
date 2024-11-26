import request from '@/utils/request'

// 查询赛事登记列表
export function listCompetitions2(query) {
  return request({
    url: '/system/competitions2/list',
    method: 'get',
    params: query
  })
}

// 查询赛事登记详细
export function getCompetitions2(id) {
  return request({
    url: '/system/competitions2/' + id,
    method: 'get'
  })
}

// 新增赛事登记
export function addCompetitions2(data) {
  return request({
    url: '/system/competitions2',
    method: 'post',
    data: data
  })
}

// 修改赛事登记
export function updateCompetitions2(data) {
  return request({
    url: '/system/competitions2',
    method: 'put',
    data: data
  })
}

// 删除赛事登记
export function delCompetitions2(id) {
  return request({
    url: '/system/competitions2/' + id,
    method: 'delete'
  })
}
