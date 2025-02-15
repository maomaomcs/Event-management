import request from '@/utils/request'

// 查询队伍列表
export function listTeams1(query) {
  return request({
    url: '/system/teams1/list',
    method: 'get',
    params: query
  })
}

// 查询队伍详细
export function getTeams1(id) {
  return request({
    url: '/system/teams1/' + id,
    method: 'get'
  })
}

// 新增队伍
export function addTeams1(data) {
  return request({
    url: '/system/teams1',
    method: 'post',
    data: data
  })
}

// 修改队伍
export function updateTeams1(data) {
  return request({
    url: '/system/teams1',
    method: 'put',
    data: data
  })
}

// 删除队伍
export function delTeams1(id) {
  return request({
    url: '/system/teams1/' + id,
    method: 'delete'
  })
}
