import request from '@/utils/request'

// 查询学生列表列表
export function listStudents(query) {
  return request({
    url: '/system/students/list',
    method: 'get',
    params: query
  })
}

// 查询学生列表详细
export function getStudents(studentId) {
  return request({
    url: '/system/students/' + studentId,
    method: 'get'
  })
}

// 新增学生列表
export function addStudents(data) {
  return request({
    url: '/system/students',
    method: 'post',
    data: data
  })
}

// 修改学生列表
export function updateStudents(data) {
  return request({
    url: '/system/students',
    method: 'put',
    data: data
  })
}

// 删除学生列表
export function delStudents(studentId) {
  return request({
    url: '/system/students/' + studentId,
    method: 'delete'
  })
}
