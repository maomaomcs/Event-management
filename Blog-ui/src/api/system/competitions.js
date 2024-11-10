import request from '@/utils/request'

// 查询赛事记录1列表
export function listCompetitions(query) {
  return request({
    url: '/system/competitions/list',
    method: 'get',
    params: query
  })
}

// 查询赛事记录1详细
export function getCompetitions(id) {
  return request({
    url: '/system/competitions/' + id,
    method: 'get'
  })
}

// 新增赛事记录1
export function addCompetitions(data) {
  return request({
    url: '/system/competitions',
    method: 'post',
    data: data
  })
}

// 修改赛事记录1
export function updateCompetitions(data) {
  return request({
    url: '/system/competitions',
    method: 'put',
    data: data
  })
}

// 删除赛事记录1
export function delCompetitions(id) {
  return request({
    url: '/system/competitions/' + id,
    method: 'delete'
  })
}
