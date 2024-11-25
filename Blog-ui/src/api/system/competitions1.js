import request from '@/utils/request'

// 查询赛事列表1列表
export function listCompetitions1(query) {
  return request({
    url: '/system/competitions1/list',
    method: 'get',
    params: query
  })
}

// 查询赛事列表1详细
export function getCompetitions1(id) {
  return request({
    url: '/system/competitions1/' + id,
    method: 'get'
  })
}

// 新增赛事列表1
export function addCompetitions1(data) {
  return request({
    url: '/system/competitions1',
    method: 'post',
    data: data
  })
}

// 修改赛事列表1
export function updateCompetitions1(data) {
  return request({
    url: '/system/competitions1',
    method: 'put',
    data: data
  })
}

// 删除赛事列表1
export function delCompetitions1(id) {
  return request({
    url: '/system/competitions1/' + id,
    method: 'delete'
  })
}
